package com.brenno.entrega.controller;

import com.brenno.entrega.DTO.solicitacao.PedidoEntregaResponseDTO;
import com.brenno.entrega.DTO.solicitacao.ProdutoPedidoResponseDTO;
import com.brenno.entrega.DTO.solicitacao.SolicitacaoEntregaResponseDTO;
import com.brenno.entrega.model.Pedido;
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
        List<ProdutoPedidoResponseDTO> produtos = pedido.getItens().stream()
                        .map(item ->
                                new ProdutoPedidoResponseDTO(
                                        item.getProduto().getNome(),
                                        item.getQuantidade(),
                                        item.getValorUnitario(),
                                        item.getDesconto()
                                )
                        ).toList();

        PedidoEntregaResponseDTO response = new PedidoEntregaResponseDTO(
                        pedido.getIdPedido(),
                        pedido.getUsuario().getNome(),
                        pedido.getUsuario().getTelefone(),
                        pedido.getEndereco().getRua(),
                        pedido.getEndereco().getNumero(),
                        pedido.getEndereco().getBairro(),
                        pedido.getEndereco().getCidade(),
                        pedido.getValorCompra(),
                        produtos
                );

        return ResponseEntity.ok(response);
    }
}