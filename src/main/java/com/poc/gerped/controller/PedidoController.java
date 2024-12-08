package com.poc.gerped.controller;

import com.poc.gerped.DTO.Response.BaseResponseDTO;
import com.poc.gerped.DTO.Response.PedidoResponse;
import com.poc.gerped.DTO.Resquest.PedidoRequest;
import com.poc.gerped.exception.ServicosException;
import com.poc.gerped.service.PedidoService;
import com.poc.gerped.util.Constantes;
import com.poc.gerped.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<BaseResponseDTO> criarPedido(@RequestBody PedidoRequest pedidoRequest) {
        Long numeroPedido = 0L;
        try {
            numeroPedido =  pedidoService.salvar(pedidoRequest);
        } catch (ServicosException e) {
            return Utils.tratarErroStatus400Status500(e);
        }
        return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_200, Constantes.MENSAGEM_SUCESSO, Constantes.NUMERO_STATUS_200, numeroPedido, HttpStatus.OK);
    }

    @GetMapping("/{numeroPedido}")
    public ResponseEntity<BaseResponseDTO> buscarPedido(@PathVariable Long numeroPedido) {
        PedidoResponse pedidoResponse;
        try {
            pedidoResponse =  pedidoService.buscarPedido(numeroPedido);
            return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_200, Constantes.MENSAGEM_SUCESSO, Constantes.NUMERO_STATUS_200, pedidoResponse, HttpStatus.OK);
        } catch (ServicosException e) {
            return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_500, e.getMessage(), Constantes.NUMERO_STATUS_500, numeroPedido, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
