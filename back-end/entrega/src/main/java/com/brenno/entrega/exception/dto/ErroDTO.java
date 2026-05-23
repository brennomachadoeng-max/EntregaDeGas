package com.brenno.entrega.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {
    private String status;
    private String mensagem;
}
