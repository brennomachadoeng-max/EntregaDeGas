package com.brenno.entrega.controller;

import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.model.Pedido;
import com.brenno.entrega.service.EntregadorService;
import com.brenno.entrega.service.PedidoService;
import com.brenno.entrega.service.SolicitacaoEntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;
    private final PedidoService pedidoService;
    private final SolicitacaoEntregaService solicitacaoEntregaService;

    public EntregadorController(
            EntregadorService entregadorService,
            PedidoService pedidoService, SolicitacaoEntregaService solicitacaoEntregaService) {
        this.entregadorService = entregadorService;
        this.pedidoService = pedidoService;
        this.solicitacaoEntregaService = solicitacaoEntregaService;
    }

    @PostMapping
    public ResponseEntity<Entregador> save(
            @RequestBody Entregador entregador) {
        Entregador salvo = entregadorService.save(entregador);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/{id}/localizacao")
    public ResponseEntity<Entregador> atualizarLocalizacao(@PathVariable Integer id, @RequestBody Entregador entregador) {
        Entregador atualizado = entregadorService.atualizarLocalizcao(id, entregador);
        return ResponseEntity.ok(atualizado);
    }

    @PostMapping("/{pedidoId}/solicitarEntregador")
    public ResponseEntity<?> solicitarEntregador(@PathVariable Integer pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
        List<Entregador> entregadors = entregadorService.solicitarEntregador(pedido);
        solicitacaoEntregaService.criarSolicitacoes(pedido, entregadors);
        return ResponseEntity.ok().build();
    }
}