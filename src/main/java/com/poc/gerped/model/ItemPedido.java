package com.poc.gerped.model;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ItemPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = CascadeType.PERSIST)
    private Produto produto;
    private Integer quantidade;
    @ManyToOne
    private Pedido pedido;

}
