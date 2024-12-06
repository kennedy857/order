package com.poc.gerped.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponse {

    private ProdutoResponse produto;
    private Integer quantidade;

}
