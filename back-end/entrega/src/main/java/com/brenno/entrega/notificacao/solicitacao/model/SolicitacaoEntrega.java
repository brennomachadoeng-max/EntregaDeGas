package com.brenno.entrega.notificacao.solicitacao.model;

import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import com.brenno.entrega.pedido.model.Pedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "solicitacao_entrega", schema = "entrega_gas")
public class SolicitacaoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_solicitacao")
    private Integer idSolicitacao;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_entregador", nullable = false)
    private Entregador entregador;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StatusSolicitacao status;

    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;
}