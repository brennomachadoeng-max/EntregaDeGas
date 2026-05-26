package com.brenno.entrega.entregador.controller;

import com.brenno.entrega.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import com.brenno.entrega.entregador.dto.EntregadorCadastroDTO;
import com.brenno.entrega.entregador.dto.EntregadorResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.entregador.service.EntregadorService;
import com.brenno.entrega.prdido.service.PedidoService;
import com.brenno.entrega.notificacao.solicitacao.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;

    }

    @PostMapping("/cadastro")
    public ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto) {
        Entregador salvo = entregadorService.cadastrar(dto);
        if (salvo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EntregadorResponseDTO response = new EntregadorResponseDTO(salvo.getIdEntregador(), salvo.getNome(), salvo.getTelefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}