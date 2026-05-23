package com.brenno.entrega.notificacao.solicitacao.service;

import com.brenno.entrega.notificacao.solicitacao.dto.SolicitacaoEntregaResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.notificacao.solicitacao.model.SolicitacaoEntrega;
import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import com.brenno.entrega.notificacao.solicitacao.repository.SolicitacaoEntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SolicitacaoEntregaService {

    private final SolicitacaoEntregaRepository repository;

    public SolicitacaoEntregaService(SolicitacaoEntregaRepository repository) {
        this.repository = repository;
    }

    public SolicitacaoEntrega save(SolicitacaoEntrega solicitacao) {
        return repository.save(solicitacao);
    }

    public void criarSolicitacoes(Pedido pedido, List<Entregador> entregadores) {
        entregadores.forEach(entregador -> {
            SolicitacaoEntrega solicitacao = new SolicitacaoEntrega();
            solicitacao.setPedido(pedido);
            solicitacao.setEntregador(entregador);
            solicitacao.setStatus(StatusSolicitacao.PENDENTE);
            solicitacao.setDataSolicitacao(LocalDateTime.now());
            repository.save(solicitacao);
        });
    }

    public List<SolicitacaoEntregaResponseDTO> listaDeEntregadoresProximo(Integer idPedido) {
        return repository.findByPedidoIdPedidoAndStatus(idPedido, StatusSolicitacao.PENDENTE).stream()
                .map(solicitacao ->
                        new SolicitacaoEntregaResponseDTO(
                                solicitacao.getIdSolicitacao(),
                                solicitacao.getEntregador().getIdEntregador(),
                                solicitacao.getEntregador().getNome(),
                                solicitacao.getStatus(),
                                solicitacao.getDataSolicitacao())).toList();
    }

    public SolicitacaoEntrega atualizarStatusSolicitacao(Integer idSolicitacao, StatusSolicitacao status) {
        SolicitacaoEntrega solicitacao = repository.findById(idSolicitacao).orElseThrow();
        solicitacao.setStatus(status);
        return repository.save(solicitacao);
    }
}