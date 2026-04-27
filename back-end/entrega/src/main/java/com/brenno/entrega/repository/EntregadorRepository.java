package com.brenno.entrega.repository;

import com.brenno.entrega.model.Entregador;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Integer> {
    /*
    save(entregador)
    findById(id)
    findAll()
    deleteById(id)
    delete(entregador)
    existsById(id)
    count()
    findAllById(ids)
    */
    @Query("""
    SELECT e FROM Entregador e
    WHERE ST_DWithin(e.localizacao, :localizacao, :raio) = true
""")
    List<Entregador> findEntregadoresProximos(Point localizacao, double raio);
}
