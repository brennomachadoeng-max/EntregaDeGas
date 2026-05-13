package com.brenno.entrega.controller;

import com.brenno.entrega.model.PedidoProduto;
import com.brenno.entrega.service.PedidoProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/addProduto")
public class PedidoProdutoController {
    private final PedidoProdutoService pedidoProdutoService;

    public PedidoProdutoController(PedidoProdutoService pedidoProdutoService) {
        this.pedidoProdutoService = pedidoProdutoService;
    }

    @PostMapping
    public ResponseEntity<List<PedidoProduto>> addProduto(@RequestBody List<PedidoProduto> itens){
        itens.stream().forEach(item -> pedidoProdutoService.save(item));
        return ResponseEntity.ok().body(itens);
    }
}
