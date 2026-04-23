package com.brenno.entrega.repository;

import com.brenno.entrega.model.PedidoProduto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, Integer> {
    /*
    save(pedidoProduto)
    findById(id)
    findAll()
    deleteById(id)
    delete(pedidoProduto)
    existsById(id)
    count()
    findAllById(ids)
    */
}
