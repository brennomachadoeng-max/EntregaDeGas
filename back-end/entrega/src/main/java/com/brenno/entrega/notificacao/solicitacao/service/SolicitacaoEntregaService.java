package com.brenno.entrega.notificacao.solicitacao.service;

import com.brenno.entrega.logistica.rastreamento.service.PosicaoEntregadorService;
import com.brenno.entrega.notificacao.solicitacao.dto.SolicitacaoEntregaResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.notificacao.solicitacao.model.SolicitacaoEntrega;
import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import com.brenno.entrega.notificacao.solicitacao.repository.SolicitacaoEntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoEntregaService {

    private final SolicitacaoEntregaRepository repository;
    private final PosicaoEntregadorService posicaoEntregadorService;

    public SolicitacaoEntregaService(SolicitacaoEntregaRepository repository, PosicaoEntregadorService posicaoEntregadorService) {
        this.repository = repository;
        this.posicaoEntregadorService = posicaoEntregadorService;
    }

    public SolicitacaoEntrega save(SolicitacaoEntrega solicitacao) {
        return repository.save(solicitacao);
    }
    public void criarSolicitacoes(Pedido pedido, List<Entregador> entregadores) {
        entregadores.forEach(entregador -> insertSolicitacao(pedido, entregador));
    }
    public void insertSolicitacao(Pedido pedido, Entregador entregador) {
        SolicitacaoEntrega solicitacao = new SolicitacaoEntrega();
        solicitacao.setPedido(pedido);
        solicitacao.setEntregador(entregador);
        solicitacao.setStatus(StatusSolicitacao.PENDENTE);
        solicitacao.setDataSolicitacao(LocalDateTime.now());
        repository.save(solicitacao);
    }
    public List<SolicitacaoEntrega> BuscarListaSolicitacoes(Integer idPedido) {
        return repository.findByPedidoIdPedidoAndStatus(idPedido, StatusSolicitacao.PENDENTE);
    }
    public SolicitacaoEntregaResponseDTO SolicitacaoEntregaParaSolicitacaoEntregaResponseDTO(SolicitacaoEntrega solicitacao) {
        return new SolicitacaoEntregaResponseDTO(
                solicitacao.getIdSolicitacao(),
                solicitacao.getEntregador().getIdEntregador(),
                solicitacao.getEntregador().getNome(),
                solicitacao.getStatus(),
                solicitacao.getDataSolicitacao());
    }
    public List<SolicitacaoEntregaResponseDTO> listaDeEntregadoresProximo(Integer idPedido) {
        return repository.findByPedidoIdPedidoAndStatus(idPedido, StatusSolicitacao.PENDENTE).stream()
                .map(this::SolicitacaoEntregaParaSolicitacaoEntregaResponseDTO).toList();
    }
    public SolicitacaoEntrega buscarSolicitacaoIdEntrega(Integer idSolicitacao) {
        return repository.findById(idSolicitacao).orElseThrow();
    }
    public SolicitacaoEntrega atualizarStatusSolicitacao(Integer idSolicitacao, StatusSolicitacao status) {
        SolicitacaoEntrega solicitacao = buscarSolicitacaoIdEntrega(idSolicitacao);
        solicitacao.setStatus(status);
        return save(solicitacao);
    }
    public List<Entregador> solicitarEntregador(Pedido pedido) {
        return posicaoEntregadorService.buscarEntregadorProximo(pedido, 5000);
    }
}