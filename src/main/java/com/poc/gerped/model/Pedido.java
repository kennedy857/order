package com.poc.gerped.model;

import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@AllArgsConstructor
@Data
@Entity
public class Pedido {

    @Id
    private Long id;
    private long numero;
    @OneToOne
    private Cliente cliente;
    @OneToMany
    @JoinColumn(name="pedido_id")
    private List<ItemPedido> itens;
}
