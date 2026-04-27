package com.brenno.entrega.Controller;

import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.save(usuario);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/verificar")
    public ResponseEntity<Usuario> verificarLogin(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOpt =
                usuarioService.findByEmail(usuario.getEmail());
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario user = usuarioOpt.get();
        if (user.isAtivo()) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.status(401).build();
    }
}
