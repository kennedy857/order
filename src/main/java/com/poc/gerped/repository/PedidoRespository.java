package com.poc.gerped.repository;

import com.poc.gerped.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Long> {

    Pedido findByNumero(Long numero);

    List<Pedido> findByClienteDocumento(String cliente);
}
