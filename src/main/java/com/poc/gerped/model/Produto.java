package com.poc.gerped.model;

import java.math.BigDecimal;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Long codigo;
    private String descricao;
    private BigDecimal valorUnitario;
}
