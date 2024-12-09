package com.poc.gerped.repository;

import com.poc.gerped.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;

@Repository
public interface PedidoRespository extends JpaRepository<Pedido, Long> {

    Pedido findByNumero(Long numero);

    List<Pedido> findByClienteDocumento(String cliente);

    @Query(value =
                    " SELECT"+
                    "    md5(string_agg(" +
                    "        CONCAT(" +
                    "            ip.produto_codigo, '-', " +
                    "            ip.quantidade" +
                    "        ), '|'" +
                    "        ORDER BY ip.produto_codigo, ip.quantidade" +
                    "    )) AS hash_item_pedido" +
                    " FROM " +
                    "    pedido p" +
                    " JOIN " +
                    "    cliente c ON p.cliente_id = c.id" +
                    " JOIN " +
                    "    item_pedido ip ON ip.pedido_id = p.id" +
                    " WHERE " +
                    "    c.documento = :cliente and p.status like 'PENDENTE'" +
                    " GROUP BY p.id;",nativeQuery = true)
    HashSet<String> findHashPedidoPendenteByClient(@Param("cliente") String cliente);
}
