package com.brenno.entrega.pedido.dto;

import com.brenno.entrega.pedido.itemPedido.dto.ItemPedidoRequest;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PedidoRequest {
    private Integer usuarioId;
    private Integer empresaId;
    private Integer enderecoId;
    private BigDecimal valorCompra;
    private List<ItemPedidoRequest> produtos;
}
