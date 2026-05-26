package com.brenno.entrega.auth.controller;

import com.brenno.entrega.auth.dto.LoginRequest;
import com.brenno.entrega.auth.dto.LoginResponse;
import com.brenno.entrega.auth.service.AuthService;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.entregador.service.EntregadorService;
import com.brenno.entrega.user.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request.getLogin(), request.getSenha()));
    }
}
