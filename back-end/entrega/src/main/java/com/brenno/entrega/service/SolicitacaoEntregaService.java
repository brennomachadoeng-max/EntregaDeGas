package com.brenno.entrega.service;

import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.model.Pedido;
import com.brenno.entrega.model.SolicitacaoEntrega;
import com.brenno.entrega.model.StatusSolicitacao;
import com.brenno.entrega.repository.SolicitacaoEntregaRepository;
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

    public List<SolicitacaoEntrega> listaDeEntregadoresProximo(Integer idPedido) {
        return repository.findByPedidoIdPedidoAndStatus(idPedido, StatusSolicitacao.PENDENTE);
    }

    public SolicitacaoEntrega atualizarStatusSolicitacao(Integer idSolicitacao, StatusSolicitacao status) {
        SolicitacaoEntrega solicitacao = repository.findById(idSolicitacao).orElseThrow();
        solicitacao.setStatus(status);
        return repository.save(solicitacao);
    }
}