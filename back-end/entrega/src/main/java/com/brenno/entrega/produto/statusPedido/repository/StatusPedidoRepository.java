package com.brenno.entrega.produto.statusPedido.repository;

import com.brenno.entrega.produto.statusPedido.model.StatusPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusPedidoRepository extends JpaRepository<StatusPedido, Integer> {
    /*
    save(statusPedido)
    findById(id)
    findAll()
    deleteById(id)
    delete(statusPedido)
    existsById(id)
    count()
    findAllById(ids)
    */
}
