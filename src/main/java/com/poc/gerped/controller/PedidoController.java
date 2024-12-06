package com.poc.gerped.controller;

import com.poc.gerped.DTO.Resquest.PedidoRequest;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @PostMapping
    public PedidoRequest criarPedido(@Valid  @RequestBody PedidoRequest pedidoRequest ) {
        System.out.println("Pedido recebido: " + pedidoRequest);

        return pedidoRequest;
    }

}
