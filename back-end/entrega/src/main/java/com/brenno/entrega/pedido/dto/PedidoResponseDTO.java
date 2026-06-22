package com.brenno.entrega.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PedidoResponseDTO {

    private Integer pedidoId;
    private String mensagem;
}