package com.poc.gerped.DTO.Response;


import com.poc.gerped.enums.Status;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoResponse {

    private long numero;
    @Enumerated(EnumType.STRING)
    private Status status;
    private ClienteResponse cliente;
    private List<ItemPedidoResponse> itens;
}
