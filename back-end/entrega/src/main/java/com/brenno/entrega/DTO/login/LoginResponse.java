package com.brenno.entrega.DTO.login;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private Integer idUsuario;
    private String nome;
    private String email;
}
