package com.poc.gerped.DTO.Resquest;

import lombok.Data;

import java.util.Objects;

@Data
public class ProdutoRequest {

    private Long codigo;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ProdutoRequest that = (ProdutoRequest) o;
        return Objects.equals(codigo, that.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codigo);
    }
}
