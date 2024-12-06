package com.poc.gerped.model;

import java.math.BigDecimal;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@Data
@Entity(name = "produto")
public class Produto {

    @Id
    private Long id;
    private Long codigo;
    private String descricao;
    private BigDecimal valorUnitario;
}
