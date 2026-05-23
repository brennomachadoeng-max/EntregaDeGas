package com.brenno.entrega.entregador.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EntregadorResponseDTO {

    private Integer idEntregador;
    private String nome;
    private String telefone;
}