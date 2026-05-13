package com.brenno.entrega.controller;

import com.brenno.entrega.model.Produto;
import com.brenno.entrega.service.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto salvo = produtoService.save(produto);
        return ResponseEntity.status(201).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Produto>> listar() {
        return ResponseEntity.ok(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscar(@PathVariable Integer id) {
        return produtoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
            @PathVariable Integer id,
            @RequestBody Produto produto
    ) {
        Produto atualizado = produtoService.update(id, produto);
        return ResponseEntity.ok(atualizado);
    }


}