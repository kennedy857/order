package com.poc.gerped.controller;

import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.service.PedidoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public Long criarPedido(@RequestBody PedidoRequest pedidoRequest ) {
       pedidoService.salvar(pedidoRequest);

       return pedidoService.salvar(pedidoRequest);
    }

    @GetMapping("/{numeroPedido}")
    public PedidoResponse buscarPedido(@PathVariable Long numeroPedido){

        return pedidoService.buscarPedido(numeroPedido);
    }
}
