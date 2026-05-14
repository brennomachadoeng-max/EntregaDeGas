package com.brenno.entrega.DTO.entregador;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntregadorLoginResponse {

    private Integer idEntregador;
    private String nome;
    private String telefone;
}
