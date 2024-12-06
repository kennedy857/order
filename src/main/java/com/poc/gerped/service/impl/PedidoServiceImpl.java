package com.poc.gerped.service.impl;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.model.Pedido;
import com.poc.gerped.model.SequencialPedido;
import com.poc.gerped.repository.PedidoRespository;
import com.poc.gerped.repository.SequencialPedidoRepository;
import com.poc.gerped.service.PedidoService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

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
    public PedidoResponse buscarPedido(Long numeroPedido) {

        return modelMapper.map(pedidoRespository.findByNumero(numeroPedido), PedidoResponse.class);
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
