package com.brenno.entrega.repository;

import com.brenno.entrega.model.SolicitacaoEntrega;
import com.brenno.entrega.model.StatusSolicitacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SolicitacaoEntregaRepository extends JpaRepository<SolicitacaoEntrega, Integer> {

    List<SolicitacaoEntrega> findByPedidoIdPedidoAndStatus(Integer pedidoId, StatusSolicitacao status);
}
