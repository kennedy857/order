package com.poc.gerped.DTO.Resquest;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
public class ItemPedidoRequest {

    private ProdutoRequest produto;
    private Integer quantidade;

}
