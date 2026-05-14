package com.brenno.entrega.DTO.pedido;

import com.brenno.entrega.DTO.PedidoProdutoRequest;
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
    private List<PedidoProdutoRequest> produtos;
}
