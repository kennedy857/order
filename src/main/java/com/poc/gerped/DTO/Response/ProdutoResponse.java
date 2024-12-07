package com.poc.gerped.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {

    private Long codigo;
    private String descricao;
}
