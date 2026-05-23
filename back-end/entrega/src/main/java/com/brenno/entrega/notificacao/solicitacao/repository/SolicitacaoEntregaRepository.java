package com.brenno.entrega.notificacao.solicitacao.repository;

import com.brenno.entrega.notificacao.solicitacao.model.SolicitacaoEntrega;
import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoEntregaRepository extends JpaRepository<SolicitacaoEntrega, Integer> {

    List<SolicitacaoEntrega> findByPedidoIdPedidoAndStatus(Integer pedidoId, StatusSolicitacao status);
}
