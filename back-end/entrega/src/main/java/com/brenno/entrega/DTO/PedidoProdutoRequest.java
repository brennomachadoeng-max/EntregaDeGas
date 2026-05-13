package com.brenno.entrega.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PedidoProdutoRequest {
    private Integer produtoId;
    private Integer quantidade;
    private BigDecimal desconto;
}
