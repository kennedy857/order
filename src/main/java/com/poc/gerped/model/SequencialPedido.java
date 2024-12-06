package com.poc.gerped.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SequencialPedido {

    @Id
    private int ano;
    private int sequencial;

}