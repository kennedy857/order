package com.poc.gerped.model;

import lombok.*;
import jakarta.persistence.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @ManyToOne
    private Produto produto;
    private Integer quantidade;
    @ManyToOne
    private Pedido pedido;
    private BigDecimal valorTotalItem;

}
