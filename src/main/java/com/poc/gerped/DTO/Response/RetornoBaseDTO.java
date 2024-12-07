package com.poc.gerped.DTO.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RetornoBaseDTO {
    private Integer id;
    private String numero;
    private String mensagem;
}
