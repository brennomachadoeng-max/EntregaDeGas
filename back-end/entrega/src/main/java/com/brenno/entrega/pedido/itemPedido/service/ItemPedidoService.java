package com.brenno.entrega.pedido.itemPedido.service;

import com.brenno.entrega.pedido.itemPedido.dto.ItemPedidoRequest;
import com.brenno.entrega.pedido.itemPedido.dto.ProdutoItemPedidoDTO;
import com.brenno.entrega.pedido.model.Pedido;
import com.brenno.entrega.pedido.itemPedido.model.ItemPedido;
import com.brenno.entrega.produto.model.Produto;
import com.brenno.entrega.produto.service.ProdutoService;
import com.brenno.entrega.pedido.itemPedido.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository pedidoProdutoRepository;
    private final ProdutoService produtoService;

    public ItemPedidoService(ItemPedidoRepository pedidoProdutoRepository, ProdutoService produtoService) {
        this.pedidoProdutoRepository = pedidoProdutoRepository;
        this.produtoService = produtoService;
    }

    public ItemPedido save(ItemPedido pedidoProduto) {
        return pedidoProdutoRepository.save(pedidoProduto);
    }

    public List<ItemPedido> findAll() {
        return pedidoProdutoRepository.findAll();
    }

    public Optional<ItemPedido> findById(Integer id) {
        return pedidoProdutoRepository.findById(id);
    }

    public void delete(ItemPedido pedidoProduto) {
        pedidoProdutoRepository.delete(pedidoProduto);
    }

    public void adicionandoProdutoAoPedido(Pedido pedido, List<ItemPedidoRequest> produtos) {
        produtos.forEach(item -> {
            Produto produto = produtoService.findById(item.getProdutoId());

            ItemPedido pedidoProduto = new ItemPedido();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorUnitario(produto.getValor());
            pedidoProduto.setDesconto(item.getDesconto());

            save(pedidoProduto);
        });
    }

    public ProdutoItemPedidoDTO itemParaPedidoEntregaResponseDTO(ItemPedido item){
        return new ProdutoItemPedidoDTO(
                    item.getProduto().getNome(),
                    item.getQuantidade(),
                    item.getValorUnitario(),
                    item.getDesconto()
            );
    }


}
