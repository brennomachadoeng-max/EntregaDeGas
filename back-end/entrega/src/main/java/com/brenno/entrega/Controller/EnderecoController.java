package com.brenno.entrega.Controller;

import com.brenno.entrega.model.Endereco;
import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.service.EnderecoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService enderecoService;

    public EnderecoController(EnderecoService enderecoService) {
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Endereco> cadastrar(
            @RequestBody Endereco endereco
    ) {
        Endereco salvo = enderecoService.save(endereco);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Endereco>> buscarPorUsuario(
            @PathVariable Integer id
    ) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(id);
        return ResponseEntity.ok(enderecoService.findByUsuario(usuario));
    }
}
