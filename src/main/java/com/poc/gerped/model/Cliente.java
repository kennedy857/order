package com.poc.gerped.model;

import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@Data
@Entity
public class Cliente {

    @Id
    private Long id;
    private String nome;
    private String documento;
}
