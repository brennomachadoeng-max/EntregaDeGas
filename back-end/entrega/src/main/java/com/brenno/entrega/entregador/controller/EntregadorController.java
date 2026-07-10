package com.brenno.entrega.entregador.controller;

import com.brenno.entrega.entregador.dto.EntregadorCadastroDTO;
import com.brenno.entrega.entregador.dto.EntregadorResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.entregador.service.EntregadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;

    }

    @PostMapping("/cadastro")
    public ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto) {
        EntregadorResponseDTO salvo = entregadorService.cadastrar(dto);
        if (salvo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<EntregadorResponseDTO> alternarStatusOnline(@PathVariable Integer id) {
        EntregadorResponseDTO entregador = entregadorService.alternarStatusOnline(id);
        return ResponseEntity.ok(entregador);
    }


}