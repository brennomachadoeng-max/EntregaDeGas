package com.brenno.entrega.DTO.produto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProdutoResponseDTO {

    private Integer idProduto;
    private String nome;
    private BigDecimal valor;
}