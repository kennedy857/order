package com.poc.gerped.DTO.Resquest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
public class PedidoRequest {

    private ClienteRequest cliente;
    private List<ItemPedidoRequest> itens;
}
