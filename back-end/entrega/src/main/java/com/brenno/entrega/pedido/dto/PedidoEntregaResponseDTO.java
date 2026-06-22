package com.brenno.entrega.pedido.dto;

import com.brenno.entrega.pedido.itemPedido.dto.ProdutoItemPedidoDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
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
    private LocalDateTime dataPedido;
}