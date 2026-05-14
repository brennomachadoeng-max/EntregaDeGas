package com.brenno.entrega.DTO.solicitacao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
public class PedidoEntregaResponseDTO {
    private Integer idPedido;
    private String nomeCliente;
    private String telefoneCliente;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private BigDecimal valorCompra;
    private List<ProdutoPedidoResponseDTO> produtos;
}