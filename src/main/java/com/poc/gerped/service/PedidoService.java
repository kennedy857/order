package com.poc.gerped.service;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;

public interface PedidoService {
    Long salvar(PedidoRequest pedidoRequest);

    PedidoResponse buscarPedido(Long numeroPedido);
}
