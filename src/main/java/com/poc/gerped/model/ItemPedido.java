package com.poc.gerped.model;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@Data
@Entity
public class ItemPedido {

    @Id
    private Long id;
    @OneToOne
    private Produto produto;
    private Integer quantidade;
    @ManyToOne
    private Pedido pedido;

}
