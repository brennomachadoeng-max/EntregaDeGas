package com.brenno.entrega.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoRequest {
    private Integer usuarioId;
    private Integer empresaId;
    private Integer enderecoId;
    private Integer statusId;
}
