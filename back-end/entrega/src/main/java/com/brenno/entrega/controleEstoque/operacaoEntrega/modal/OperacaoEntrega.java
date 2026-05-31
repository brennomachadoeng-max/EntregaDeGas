package com.brenno.entrega.controleEstoque.operacaoEntrega.modal;

import com.brenno.entrega.controleEstoque.movimentacao.model.Movimentacao;
import com.brenno.entrega.entregador.model.Entregador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "operacao_entrega", schema = "entrega_gas")
public class OperacaoEntrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_operacao_entrega")
    private Integer idOperacaoEntrega;

    @ManyToOne
    @JoinColumn(name = "id_entregador", nullable = false)
    private Entregador entregador;

    @Column(name = "data_hora", insertable = false, updatable = false)
    private LocalDateTime dataHora;

    @OneToMany(mappedBy = "operacaoEntrega")
    public List<Movimentacao> movimentacoes;
}
