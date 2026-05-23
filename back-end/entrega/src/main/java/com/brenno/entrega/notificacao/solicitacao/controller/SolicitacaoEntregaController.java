package com.brenno.entrega.notificacao.solicitacao.controller;

import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.prdido.dto.PedidoEntregaResponseDTO;
import com.brenno.entrega.prdido.itemPedido.dto.ProdutoItemPedidoDTO;
import com.brenno.entrega.notificacao.solicitacao.dto.SolicitacaoEntregaResponseDTO;
import com.brenno.entrega.prdido.itemPedido.model.ItemPedido;
import com.brenno.entrega.prdido.itemPedido.service.ItemPedidoService;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.notificacao.solicitacao.model.SolicitacaoEntrega;
import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import com.brenno.entrega.prdido.service.PedidoService;
import com.brenno.entrega.notificacao.solicitacao.service.SolicitacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoEntregaController {

    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final PedidoService pedidoService;
    private final ItemPedidoService itemPedidoService;

    public SolicitacaoEntregaController(SolicitacaoEntregaService solicitacaoEntregaService, PedidoService pedidoService, ItemPedidoService itemPedidoService) {

        this.solicitacaoEntregaService = solicitacaoEntregaService;
        this.pedidoService = pedidoService;
        this.itemPedidoService = itemPedidoService;
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<SolicitacaoEntregaResponseDTO>> listarSolicitacoesPedido(@PathVariable Integer pedidoId) {
        List<SolicitacaoEntregaResponseDTO> response = solicitacaoEntregaService.listaDeEntregadoresProximo(pedidoId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}/expirar")
    public ResponseEntity<String> expirar(@PathVariable Integer id) {
        solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.EXPIRADA);
        return ResponseEntity.ok("Solicitação expirada");
    }

    @PostMapping("/{id}/recusar")
    public ResponseEntity<String> recusar(@PathVariable Integer id) {
        solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.RECUSADA);
        return ResponseEntity.ok("Solicitação recusada");
    }

    @PostMapping("/{id}/aceitar")
    public ResponseEntity<PedidoEntregaResponseDTO>
    aceitar(@PathVariable Integer id) {
        SolicitacaoEntrega solicitacao = solicitacaoEntregaService.atualizarStatusSolicitacao(id, StatusSolicitacao.ACEITA);
        Pedido pedido = solicitacao.getPedido();
        List<ProdutoItemPedidoDTO> produtos = pedido.getItens().stream().map(itemPedidoService::itemParaPedidoEntregaResponseDTO).toList();
        PedidoEntregaResponseDTO response = pedidoService.PedidoParaPedidoEntregaResponseDTO(pedido, produtos);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{pedidoId}/solicitarEntregador")
    public ResponseEntity<String> solicitarEntregador(@PathVariable Integer pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        List<Entregador> entregadores = solicitacaoEntregaService.solicitarEntregador(pedido);
        solicitacaoEntregaService.criarSolicitacoes(pedido, entregadores);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitações enviadas com sucesso");
    }
}