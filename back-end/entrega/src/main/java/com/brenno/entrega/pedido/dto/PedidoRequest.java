package com.brenno.entrega.pedido.dto;

import com.brenno.entrega.pedido.itemPedido.dto.ItemPedidoRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PedidoRequest {
    private Integer usuarioId;
    private Integer empresaId;
    private Integer enderecoId;
    private Integer statusId;
    private List<ItemPedidoRequest> produtos;
}
