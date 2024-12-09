package com.poc.gerped.DTO.Resquest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
public class ClienteRequest {

    private String nome;
    private String documento;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ClienteRequest that = (ClienteRequest) o;
        return Objects.equals(documento, that.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(documento);
    }
}
