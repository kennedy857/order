package com.poc.gerped.DTO.Resquest;


import lombok.Data;

import java.util.List;
import java.util.Objects;


@Data
public class PedidoRequest {

    private ClienteRequest cliente;
    private List<ItemPedidoRequest> itens;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PedidoRequest that = (PedidoRequest) o;
        return Objects.equals(cliente, that.cliente) && Objects.equals(itens, that.itens);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, itens);
    }
}
