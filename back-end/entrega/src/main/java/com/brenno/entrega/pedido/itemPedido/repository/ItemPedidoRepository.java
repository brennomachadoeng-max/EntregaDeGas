package com.brenno.entrega.pedido.itemPedido.repository;

import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {
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
