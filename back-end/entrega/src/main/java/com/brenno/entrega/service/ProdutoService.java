package com.brenno.entrega.service;

import com.brenno.entrega.DTO.produto.ProdutoRequestDTO;
import com.brenno.entrega.DTO.produto.ProdutoResponseDTO;
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

    public List<ProdutoResponseDTO> findAll() {
        return produtoRepository.findAll().stream().map(produto -> new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getValor())).toList();
    }

    public Produto findById(Integer id) {
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public void delete(Produto produto) {
        produtoRepository.delete(produto);
    }

    public ProdutoResponseDTO update(Integer id, ProdutoRequestDTO dto) {
        Produto produto = findById(id);
        produto.setNome(dto.getNome());
        produto.setNcm(dto.getNcm());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValor(dto.getValor());
        Produto atualizado = save(produto);
        return new ProdutoResponseDTO(atualizado.getIdProduto(), atualizado.getNome(), atualizado.getValor());
    }

    public Produto cadastrar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();
        produto.setNome(dto.getNome());
        produto.setNcm(dto.getNcm());
        produto.setQuantidade(dto.getQuantidade());
        produto.setValor(dto.getValor());
        return produtoRepository.save(produto);
    }

    public ProdutoResponseDTO buscarPorId(Integer id) {
        Produto produto = findById(id);
        return new ProdutoResponseDTO(produto.getIdProduto(), produto.getNome(), produto.getValor());
    }
}
