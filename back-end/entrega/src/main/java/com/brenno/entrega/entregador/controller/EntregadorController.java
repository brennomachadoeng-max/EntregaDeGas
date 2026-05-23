package com.brenno.entrega.entregador.controller;

import com.brenno.entrega.entregador.dto.AtualizarLocalizacaoDTO;
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
    private final PedidoService pedidoService;
    private final SolicitacaoEntregaService solicitacaoEntregaService;

    public EntregadorController(
            EntregadorService entregadorService,
            PedidoService pedidoService, SolicitacaoEntregaService solicitacaoEntregaService) {
        this.entregadorService = entregadorService;
        this.pedidoService = pedidoService;
        this.solicitacaoEntregaService = solicitacaoEntregaService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<EntregadorResponseDTO> cadastrar(@RequestBody EntregadorCadastroDTO dto) {
        Entregador salvo = entregadorService.cadastrar(dto);
        EntregadorResponseDTO response = new EntregadorResponseDTO(salvo.getIdEntregador(), salvo.getNome(), salvo.getTelefone());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{id}/localizacao")
    public ResponseEntity<EntregadorResponseDTO> atualizarLocalizacao(@PathVariable Integer id, @RequestBody AtualizarLocalizacaoDTO dto) {
        Entregador atualizado = entregadorService.atualizarLocalizacao(id, dto);
        EntregadorResponseDTO response = new EntregadorResponseDTO(atualizado.getIdEntregador(), atualizado.getNome(), atualizado.getTelefone());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{pedidoId}/solicitarEntregador")
    public ResponseEntity<String> solicitarEntregador(@PathVariable Integer pedidoId) {
        Pedido pedido = pedidoService.findById(pedidoId);
        List<Entregador> entregadores = entregadorService.solicitarEntregador(pedido);
        solicitacaoEntregaService.criarSolicitacoes(pedido, entregadores);
        return ResponseEntity.status(HttpStatus.CREATED).body("Solicitações enviadas com sucesso");
    }
}