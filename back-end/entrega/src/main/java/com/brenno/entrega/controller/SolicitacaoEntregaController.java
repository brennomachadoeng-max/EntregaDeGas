package com.brenno.entrega.controller;

import com.brenno.entrega.model.SolicitacaoEntrega;
import com.brenno.entrega.model.StatusSolicitacao;
import com.brenno.entrega.service.PedidoService;
import com.brenno.entrega.service.SolicitacaoEntregaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoEntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final PedidoService pedidoService;

    public SolicitacaoEntregaController(SolicitacaoEntregaService solicitacaoEntregaService, PedidoService pedidoService) {

        this.solicitacaoEntregaService = solicitacaoEntregaService;
        this.pedidoService = pedidoService;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<SolicitacaoEntrega>> listarSolicitacoesPedido(@PathVariable Integer pedidoId) {
        List<SolicitacaoEntrega> solicitacoes = solicitacaoEntregaService.listaDeEntregadoresProximo(pedidoId);
        return ResponseEntity.ok(solicitacoes);
    }

    @PostMapping("/{id}/expirar")
    public ResponseEntity<?> expirar(@PathVariable Integer id) {
        solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.EXPIRADA);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/recusar")
    public ResponseEntity<?> recusar(@PathVariable Integer id) {
        solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.RECUSADA);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/aceitar")
    public ResponseEntity<?> aceitar(@PathVariable Integer id) {
        SolicitacaoEntrega solicitacaoEntrega = solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.ACEITA);
        pedidoService.aceitarPedido(solicitacaoEntrega.getPedido().getIdPedido(), solicitacaoEntrega.getEntregador().getIdEntregador());

        return ResponseEntity.ok().build();
    }
}