package com.brenno.entrega.service;

import com.brenno.entrega.DTO.PedidoProdutoRequest;
import com.brenno.entrega.model.Pedido;
import com.brenno.entrega.model.PedidoProduto;
import com.brenno.entrega.model.Produto;
import com.brenno.entrega.repository.PedidoProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoProdutoService {
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoService produtoService;

    public PedidoProdutoService(PedidoProdutoRepository pedidoProdutoRepository, ProdutoService produtoService) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.produtoService = produtoService;
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

    public void adicionandoProdutoAoPedido(Pedido pedido, List<PedidoProdutoRequest> produtos) {
        produtos.forEach(item -> {
            Produto produto = produtoService.findById(item.getProdutoId());

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorUnitario(produto.getValor());
            pedidoProduto.setDesconto(item.getDesconto());

            save(pedidoProduto);
        });
    }
}
