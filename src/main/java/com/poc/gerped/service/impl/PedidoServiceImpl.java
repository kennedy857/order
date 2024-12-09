package com.poc.gerped.service.impl;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.enums.Status;
import com.poc.gerped.exception.ServicosException;
import com.poc.gerped.model.Pedido;
import com.poc.gerped.model.SequencialPedido;
import com.poc.gerped.repository.PedidoRespository;
import com.poc.gerped.repository.SequencialPedidoRepository;
import com.poc.gerped.service.PedidoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PedidoRespository pedidoRespository;

    @Autowired
    private SequencialPedidoRepository sequencialPedidoRepository;

    @Override
    public Long salvar(PedidoRequest pedidoRequest) {
        Pedido pedido = modelMapper.map(pedidoRequest, Pedido.class);
        pedido.setNumero(gerarNumeroPedido());

        return pedidoRespository.save(pedido).getNumero();
    }

    @Override
    public List<Long> salvar(List<PedidoRequest> pedidosRequest) throws ServicosException {

        pedidosRequest = validarDuplicidadePedido(pedidosRequest);

        List<Pedido> pedidos = pedidosRequest .stream() .map(p -> modelMapper.map(p, Pedido.class)) .collect(Collectors.toList());

        pedidos.forEach(p -> p.setNumero(gerarNumeroPedido()));

         return pedidoRespository.saveAll(pedidos).stream()
                 .map(Pedido::getNumero)
                 .collect(Collectors.toList());
    }

    @Override
    public List<PedidoResponse> buscarPedidosCliente(String documentoCliente) throws ServicosException {

        List<Pedido> pedidos = pedidoRespository.findByClienteDocumento(documentoCliente);

        if(Objects.isNull(pedidos)){
            return null;
        }

        return pedidos.stream().map(p -> modelMapper.map(p, PedidoResponse.class)) .toList();
    }

    @Override
    public PedidoResponse buscarPedido(Long numeroPedido) throws ServicosException {
        Pedido pedido = pedidoRespository.findByNumero(numeroPedido);

        if(Objects.isNull(pedido)){
            return null;
        }
        return modelMapper.map(pedido, PedidoResponse.class);
    }

    @Override
    public PedidoResponse consolidarPedido(Long numeroPedido) throws ServicosException {
        Pedido pedido = pedidoRespository.findByNumero(numeroPedido);

        if(Objects.nonNull(pedido)){
            if(pedido.getStatus().equals(Status.CANCELADO)){
                throw new ServicosException("Pedido Cancelado", true);
            }
            else if(pedido.getStatus().equals(Status.PENDENTE)){
                executarConsolidacao(pedido);
            }else{
                throw new ServicosException("Pedido já Consolidado", true);
            }
        }else{
            throw new ServicosException("Pedido número : "+ numeroPedido+" não encontrato", true);
        }

        return modelMapper.map(pedidoRespository.save(pedido), PedidoResponse.class);
    }

    @Override
    public PedidoResponse cancelarPedido(Long numeroPedido) throws ServicosException {
        Pedido pedido = pedidoRespository.findByNumero(numeroPedido);

        if(Objects.nonNull(pedido)){
            if(pedido.getStatus().equals(Status.CONSOLIDADO)){
                throw new ServicosException("Pedido já Consolidado", true);
            }
            else if(pedido.getStatus().equals(Status.PENDENTE)){
                pedido.setStatus(Status.CANCELADO);
            }else{
                throw new ServicosException("Pedido já Cancelado", true);
            }
        }else{
            throw new ServicosException("Pedido número : "+ numeroPedido+" não encontrato", true);
        }

        return modelMapper.map(pedidoRespository.save(pedido), PedidoResponse.class);
    }

    private void executarConsolidacao(Pedido pedido) throws ServicosException {
        try {
            pedido.getItens().forEach(item ->
                    item.setValorTotalItem(item.getProduto().getValorUnitario().multiply(new BigDecimal(item.getQuantidade())))
            );
            pedido.setStatus(Status.CONSOLIDADO);
        }catch (Exception e ){
            throw new ServicosException(e.getMessage(), false);
        }

    }

    @Transactional
    public long gerarNumeroPedido() {
        int anoAtual = Year.now().getValue();
        SequencialPedido sequencialPedido = sequencialPedidoRepository.findById(anoAtual).orElse(new SequencialPedido(anoAtual, 0));

        sequencialPedido.setSequencial(sequencialPedido.getSequencial() + 1);
        sequencialPedidoRepository.save(sequencialPedido);

        return Long.parseLong(anoAtual + String.format("%05d", sequencialPedido.getSequencial()));
    }

    private List<PedidoRequest> validarDuplicidadePedido(List<PedidoRequest> pedidoRequests){
        Map<String, HashSet<String>> mapClienteHashes = new HashMap<>();
        List<PedidoRequest> pedidosValidos = new ArrayList<>();

        pedidoRequests.forEach(pedidoRequest -> {
            if(!mapClienteHashes.containsKey(pedidoRequest.getCliente().getDocumento())) {
                mapClienteHashes.put(pedidoRequest.getCliente().getDocumento(), pedidoRespository.findHashPedidoPendenteByClient(pedidoRequest.getCliente().getDocumento()));
            }
        });

        pedidoRequests.forEach(pedidoRequest -> {
            if(!mapClienteHashes.get(pedidoRequest.getCliente().getDocumento()).contains(gerarHash(pedidoRequest))){
                pedidosValidos.add(pedidoRequest);
            }
        });

        return pedidosValidos;
    }

    private String gerarHash(PedidoRequest pedido){
        try {
            // Ordena os itens pelo código do produto para consistência
            List<String> dadosOrdenados = pedido.getItens().stream()
                    .sorted((a, b) -> Long.compare(a.getProduto().getCodigo(), b.getProduto().getCodigo()))
                    .map(item -> item.getProduto().getCodigo() + "-" + item.getQuantidade())
                    .collect(Collectors.toList());

            // Concatena os dados em uma única string, separando os itens com "|"
            String dadosConcatenados = String.join("|", dadosOrdenados);

            // Calcula o hash MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(dadosConcatenados.getBytes());

            // Converte o hash para uma string hexadecimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erro ao calcular MD5", e);
        }
    }

}
