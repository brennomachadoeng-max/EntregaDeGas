package com.brenno.entrega.pedido.itemPedido.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ItemPedidoRequest {
    private Integer produtoId;
    private Integer quantidade;
    private BigDecimal desconto;
}
