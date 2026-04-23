package com.brenno.entrega.service;

import com.brenno.entrega.model.Movimentacao;
import com.brenno.entrega.model.PedidoProduto;
import com.brenno.entrega.repository.PedidoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoProdutoService {
    public PedidoProdutoRepository pedidoProdutoRepository;
    public PedidoProdutoService(PedidoProdutoRepository pedidoProdutoRepository) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
    }

    public PedidoProduto save(PedidoProduto pedidoProduto) {
        return pedidoProdutoRepository.save(pedidoProduto);
    }

    public List<PedidoProduto> findAll() {
        return pedidoProdutoRepository.findAll();
    }

    public Optional<PedidoProduto> findById(Integer id) {
        return pedidoProdutoRepository.findById(id);
    }

    public void delete(PedidoProduto pedidoProduto) {
        pedidoProdutoRepository.delete(pedidoProduto);
    }
}
