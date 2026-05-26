package com.brenno.entrega.auth.interfac;

import com.brenno.entrega.auth.dto.LoginResponse;

public interface LoginProvider {
    boolean suporta(String login);
    LoginResponse autenticar(String login, String senha);
}
