package com.brenno.entrega.logistica.rastreamento.controller;

import com.brenno.entrega.entregador.dto.EntregadorResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import com.brenno.entrega.logistica.rastreamento.model.PosicaoEntregador;
import com.brenno.entrega.logistica.rastreamento.service.PosicaoEntregadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/localizacao")
public class PosicaoEntregadorController {

    private final PosicaoEntregadorService posicaoEntregadorService;

    public PosicaoEntregadorController(PosicaoEntregadorService posicaoEntregadorService) {
        this.posicaoEntregadorService = posicaoEntregadorService;
    }

    @PostMapping("/{id}/localizacao")
    public ResponseEntity<EntregadorResponseDTO> atualizarLocalizacao(@PathVariable Integer id, @RequestBody AtualizarLocalizacaoDTO dto) {
        PosicaoEntregador posicao = posicaoEntregadorService.atualizarLocalizacao(id, dto);
        Entregador atualizado = posicao.getEntregador();
        EntregadorResponseDTO response = new EntregadorResponseDTO(atualizado.getIdEntregador(), atualizado.getNome(), atualizado.getTelefone());
        return ResponseEntity.ok(response);
    }
}
