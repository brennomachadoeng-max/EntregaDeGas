package com.brenno.entrega.DTO.solicitacao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoPedidoResponseDTO {

    private String nomeProduto;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private BigDecimal desconto;
}