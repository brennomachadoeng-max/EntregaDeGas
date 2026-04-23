package com.brenno.entrega.service;

import com.brenno.entrega.model.Movimentacao;
import com.brenno.entrega.model.Produto;
import com.brenno.entrega.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    public final ProdutoRepository produtoRepository;
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto save(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Optional<Produto> findById(Integer id){
        return produtoRepository.findById(id);
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

    public Produto update(Integer id, Produto dados) {
        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(dados.getNome());
        produto.setNcm(dados.getNcm());
        produto.setQuantidade(dados.getQuantidade());
        produto.setValor(dados.getValor());

        return produtoRepository.save(produto);
    }
}
