package com.brenno.entrega.logistica.rastreamento.repository;

import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.logistica.rastreamento.model.PosicaoEntregador;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PosicaoEntregadorRepository extends JpaRepository<PosicaoEntregador, Integer> {
    Optional<PosicaoEntregador> findByEntregador(Entregador entregador);

    @Query(value = """
    SELECT * FROM posicao_entregador pe WHERE ST_DWithin(pe.localizacao::geography, :localizacao::geography, :raio) = true
""", nativeQuery = true)
    List<PosicaoEntregador> findEntregadoresProximos(Point localizacao, double raio);
}
