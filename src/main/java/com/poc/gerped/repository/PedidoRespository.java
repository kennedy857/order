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
            "SELECT \n" +
            "    md5(\n" +
            "        CONCAT(\n" +
            "            c.documento, '-',\n" +
            "            string_agg(\n" +
            "                CONCAT(\n" +
            "                    ip.produto_codigo, '-', \n" +
            "                    ip.quantidade\n" +
            "                ), '|'\n" +
            "                ORDER BY ip.produto_codigo, ip.quantidade\n" +
            "            )\n" +
            "        )\n" +
            "    ) AS hash_pedido_com_cliente\n" +
            "FROM \n" +
            "    pedido p\n" +
            "JOIN \n" +
            "    cliente c ON c.id = p.cliente_id\n" +
            "JOIN \n" +
            "    item_pedido ip ON ip.pedido_id = p.id\n" +
            "WHERE \n" +
            "    c.documento = '701'\n" +
            "    and p.status LIKE 'PENDENTE'\n" +
            "GROUP BY \n" +
            "    p.id, c.documento;",nativeQuery = true)
    HashSet<String> findHashPedidoPendenteByClient();
}
