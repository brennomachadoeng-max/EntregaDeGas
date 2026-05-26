package com.brenno.entrega.auth.service;

import com.brenno.entrega.auth.dto.LoginResponse;
import com.brenno.entrega.auth.interfac.LoginProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    private final List<LoginProvider> providers;

    public AuthService(List<LoginProvider> providers) {
        this.providers = providers;
    }

    public LoginResponse login(String login, String senha) {
        return providers.stream()
                .filter(provider -> provider.suporta(login)).findFirst()
                .orElseThrow(() -> new RuntimeException("Tipo de login não suportado")).autenticar(login, senha);
    }
}