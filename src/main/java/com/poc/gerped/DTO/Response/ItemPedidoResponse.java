package com.poc.gerped.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoResponse {

    private ProdutoResponse produto;
    private Integer quantidade;
    private BigDecimal valorTotalItem;

}
