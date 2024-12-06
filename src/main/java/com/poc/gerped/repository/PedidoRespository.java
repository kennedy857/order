package com.poc.gerped.repository;

import com.poc.gerped.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Long> {

    Pedido findByNumero(Long numero);
}
