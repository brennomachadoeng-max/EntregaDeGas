package com.brenno.entrega.repository;

import com.brenno.entrega.model.TipoMovimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimentacaoRepository extends JpaRepository<TipoMovimentacao, Integer> {
    /*
    save(tipoMovimentacao)
    findById(id)
    findAll()
    deleteById(id)
    delete(tipoMovimentacao)
    existsById(id)
    count()
    findAllById(ids)
    */
}
