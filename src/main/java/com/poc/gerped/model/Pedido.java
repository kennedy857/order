package com.poc.gerped.model;

import com.poc.gerped.enums.Status;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private long numero;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToOne(cascade = CascadeType.ALL)
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="pedido_id")
    private List<ItemPedido> itens;

    @PrePersist
    protected void onPrePersist() {
        if (status == null) {
            status = Status.PENDENTE;
        }
    }


}
