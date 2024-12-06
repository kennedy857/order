package com.poc.gerped.repository;

import com.poc.gerped.model.SequencialPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SequencialPedidoRepository extends JpaRepository<SequencialPedido, Integer> {
}