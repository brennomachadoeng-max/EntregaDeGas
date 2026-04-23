package com.brenno.entrega.repository;

import com.brenno.entrega.model.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {
    /*
    save(movimentacao)
    findById(id)
    findAll()
    deleteById(id)
    delete(movimentacao)
    existsById(id)
    count()
    findAllById(ids)
    */
}
