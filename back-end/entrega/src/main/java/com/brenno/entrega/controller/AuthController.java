package com.brenno.entrega.controller;

import com.brenno.entrega.DTO.login.LoginRequest;
import com.brenno.entrega.DTO.login.LoginResponse;
import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.validarLogin(request.getEmail(), request.getSenha()).orElseThrow(() -> new RuntimeException("Email ou senha invalida"));
        LoginResponse loginResponse = new LoginResponse(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
        return ResponseEntity.ok(loginResponse);
    }
}
