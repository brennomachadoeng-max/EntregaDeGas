package com.brenno.entrega.Controller;

import com.brenno.entrega.LoginRequest;
import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Optional<Usuario> usuarioOpt =
                usuarioService.validarLogin(
                        request.getEmail(),
                        request.getSenha()
                );
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401)
                    .body("Email ou senha inválidos");
        }
        Usuario usuario = usuarioOpt.get();
        usuario.setAtivo(true);
        usuarioService.save(usuario);
        return ResponseEntity.ok("Login realizado com sucesso");
        //Atividade: Retornar alguma coisa apos fazer o login, para garantir que permaneca ligada
    }
}
