package com.poc.gerped.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private ClienteResponse cliente;
    private List<ItemPedidoResponse> itens;
}
