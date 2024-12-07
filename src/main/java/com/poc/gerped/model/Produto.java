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
    private Long codigo;
    private String descricao;
    private BigDecimal valorUnitario;
}
