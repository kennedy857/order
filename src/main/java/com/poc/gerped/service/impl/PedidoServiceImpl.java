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
import java.time.Year;
import java.util.Objects;

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
}
