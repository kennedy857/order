package com.poc.gerped.service;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.exception.ServicosException;

import java.util.List;

public interface PedidoService {
    Long salvar(PedidoRequest pedidoRequest) throws ServicosException;

    List<Long> salvar(List<PedidoRequest> pedidosRequest) throws ServicosException;

    List<PedidoResponse> buscarPedidosCliente(String documentoCliente) throws ServicosException;

    PedidoResponse buscarPedido(Long numeroPedido) throws ServicosException;

    PedidoResponse consolidarPedido(Long numeroPedido) throws ServicosException;

    PedidoResponse cancelarPedido(Long numeroPedido) throws ServicosException;
}
