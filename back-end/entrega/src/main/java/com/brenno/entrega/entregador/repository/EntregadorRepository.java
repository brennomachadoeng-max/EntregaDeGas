package com.brenno.entrega.entregador.repository;

import com.brenno.entrega.entregador.model.Entregador;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

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
    Optional<Entregador> findByCpf(String cpf);
}
