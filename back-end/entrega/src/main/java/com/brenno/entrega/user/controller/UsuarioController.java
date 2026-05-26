package com.brenno.entrega.user.controller;

import com.brenno.entrega.user.dto.UsuarioCadastroDTO;
import com.brenno.entrega.user.dto.UsuarioResponseDTO;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    @PostMapping("/cadastro")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioCadastroDTO dto) {
        Usuario salvo = usuarioService.cadastrar(dto);
        if (salvo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UsuarioResponseDTO response = usuarioService.UsuarioParaUsuarioResponseDTO(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
