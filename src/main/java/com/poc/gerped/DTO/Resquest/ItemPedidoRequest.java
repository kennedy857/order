package com.poc.gerped.DTO.Resquest;


import lombok.Data;

import java.util.Objects;


@Data
public class ItemPedidoRequest {

    private ProdutoRequest produto;
    private Integer quantidade;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ItemPedidoRequest that = (ItemPedidoRequest) o;
        return Objects.equals(produto, that.produto) && Objects.equals(quantidade, that.quantidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, quantidade);
    }
}
