package com.brenno.entrega.movimentacao.model;

import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.movimentacao.tipoMovimentacao.model.TipoMovimentacao;
import com.brenno.entrega.prdido.model.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movimentacao", schema = "entrega_gas")
public class Movimentacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movimentacao")
    private Integer idMovimentacao;

    @ManyToOne
    @JoinColumn(name = "id_entregador", nullable = false)
    private Entregador entregador;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_tipo_movimentacao", nullable = false)
    private TipoMovimentacao tipoMovimentacao;

    @Column(name = "data_hora", insertable = false, updatable = false)
    private LocalDateTime dataHora;

    @Column(name = "quantidade_botijao_cheio")
    private Integer quantidadeBotijaoCheio;

    @Column(name = "quantidade_botijao_vazio")
    private Integer quantidadeBotijaoVazio;

    @Column(name = "observarcao")
    private String observacao;
}