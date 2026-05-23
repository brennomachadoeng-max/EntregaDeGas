package com.brenno.entrega.prdido.dto;

import com.brenno.entrega.prdido.itemPedido.dto.ProdutoItemPedidoDTO;
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
    private List<ProdutoItemPedidoDTO> produtos;
}