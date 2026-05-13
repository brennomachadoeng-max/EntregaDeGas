package com.brenno.entrega.DTO.login;
import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}
