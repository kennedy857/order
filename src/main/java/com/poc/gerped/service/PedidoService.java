package com.poc.gerped.service;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.exception.ServicosException;

public interface PedidoService {
    Long salvar(PedidoRequest pedidoRequest) throws ServicosException;

    PedidoResponse buscarPedido(Long numeroPedido) throws ServicosException;

    void consolidarPedido(Long numeroPedido) throws ServicosException;
}
