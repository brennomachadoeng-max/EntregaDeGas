package com.brenno.entrega.movimentacao.tipoMovimentacao.model;

import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tipo_movimentacao", schema = "entrega_gas")
public class TipoMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_movimentacao")
    private Integer idTipoMovimentacao;

    @Column(name = "tipo_movimentacao")
    private String tipoMovimentacao;

    @Column(name = "operacao_gas")
    private String operacaoGas;

    @Column(name = "operacao_vazia")
    private String operacaoVazia;
}
