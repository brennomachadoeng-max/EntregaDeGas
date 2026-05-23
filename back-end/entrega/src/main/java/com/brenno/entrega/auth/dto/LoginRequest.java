package com.brenno.entrega.auth.dto;
import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String senha;
}
