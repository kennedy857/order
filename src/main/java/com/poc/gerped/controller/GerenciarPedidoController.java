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

@RestController
@RequestMapping("/gerenciar/pedido")
public class GerenciarPedidoController {

    @Autowired
    PedidoService pedidoService;

    @PutMapping("/consolidar/{numeroPedido}")
    public ResponseEntity<BaseResponseDTO> consolidarPedido(@PathVariable Long numeroPedido) {
        PedidoResponse pedidoResponse;
        try {
            pedidoResponse = pedidoService.consolidarPedido(numeroPedido);
        } catch (ServicosException e) {
            return Utils.tratarErroStatus400Status500(e);
        }
        return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_200, Constantes.MENSAGEM_SUCESSO, Constantes.NUMERO_STATUS_200, pedidoResponse, HttpStatus.OK);
    }

    @PutMapping("/cancelar/{numeroPedido}")
    public ResponseEntity<BaseResponseDTO> cancelarPedido(@PathVariable Long numeroPedido) {
        PedidoResponse pedidoResponse;
        try {
            pedidoResponse =  pedidoService.cancelarPedido(numeroPedido);
        } catch (ServicosException e) {
            return Utils.tratarErroStatus400Status500(e);
        }
        return Utils.getBaseResponseDTOResponseEntity(Constantes.HTTP_STATUS_200, Constantes.MENSAGEM_SUCESSO, Constantes.NUMERO_STATUS_200, pedidoResponse, HttpStatus.OK);
    }
}
