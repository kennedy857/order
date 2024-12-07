package com.poc.gerped.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private long numero;
    private boolean consolidado;
    private ClienteResponse cliente;
    private List<ItemPedidoResponse> itens;
}
