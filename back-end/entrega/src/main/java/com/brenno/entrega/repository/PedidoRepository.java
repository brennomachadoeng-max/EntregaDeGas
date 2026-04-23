package com.brenno.entrega.repository;

import com.brenno.entrega.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    /*
    save(pedido)
    findById(id)
    findAll()
    deleteById(id)
    delete(pedido)
    existsById(id)
    count()
    findAllById(ids)
    */
}
