package com.brenno.entrega.controller;

import com.brenno.entrega.DTO.login.LoginRequest;
import com.brenno.entrega.DTO.login.LoginResponse;
import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.service.EntregadorService;
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
    private final EntregadorService entregadorService;

    public AuthController(UsuarioService usuarioService, EntregadorService entregadorService) {
        this.usuarioService = usuarioService;
        this.entregadorService = entregadorService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.validarLogin(request.getLogin(), request.getSenha()).orElseThrow(() -> new RuntimeException("Email ou senha invalida"));
        LoginResponse loginResponse = new LoginResponse(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/entregador/login")
    public ResponseEntity<LoginResponse> loginEntregador(@RequestBody LoginRequest request) {
        Entregador entregador = entregadorService.validarLogin(request.getLogin(), request.getSenha());
       LoginResponse loginResponse = new LoginResponse(entregador.getIdEntregador(), entregador.getNome(), entregador.getTelefone());
        return ResponseEntity.ok(loginResponse);
    }
}
