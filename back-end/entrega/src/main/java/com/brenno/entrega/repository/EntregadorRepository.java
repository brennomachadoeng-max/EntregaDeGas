package com.brenno.entrega.repository;

import com.brenno.entrega.model.Entregador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
