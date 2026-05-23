package com.brenno.entrega.user.endereco.controller;

import com.brenno.entrega.user.endereco.dto.EnderecoRequestDTO;
import com.brenno.entrega.user.endereco.dto.EnderecoResponseDTO;
import com.brenno.entrega.user.endereco.modal.Endereco;
import com.brenno.entrega.user.endereco.service.EnderecoService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<EnderecoResponseDTO> cadastrar(@RequestBody EnderecoRequestDTO dto) {
        Endereco salvo = enderecoService.cadastrar(dto);
        EnderecoResponseDTO response = new EnderecoResponseDTO(salvo.getIdEndereco(), salvo.getRua(), salvo.getNumero(), salvo.getBairro(), salvo.getCidade(), salvo.getEstado());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Endereco>> buscarPorUsuario(@PathVariable Integer id) {
        return ResponseEntity.ok(enderecoService.findByUsuario(id));
    }
}
