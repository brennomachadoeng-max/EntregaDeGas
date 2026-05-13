package com.brenno.entrega.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErroDTO {
    private String status;
    private String mensagem;
}
