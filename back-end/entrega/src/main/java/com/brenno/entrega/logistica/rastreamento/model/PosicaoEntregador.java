package com.brenno.entrega.logistica.rastreamento.model;

import com.brenno.entrega.entregador.model.Entregador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posicao_entregador", schema = "entrega_gas")
public class PosicaoEntregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_posicao_entregador")
    private Integer idPosicaoEntregador;

    @OneToOne
    @JoinColumn(name = "id_entregador", nullable = false)
    private Entregador entregador;

    @Column(name = "localizacao", columnDefinition = "geometry(Point,4326)")
    private Point localizacao;

    @Column(name = "ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;
}