package com.brenno.entrega.controleEstoque.movimentacao.controller;

import com.brenno.entrega.controleEstoque.movimentacao.model.Movimentacao;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    @PostMapping("/carga")
    public Movimentacao carga(@RequestBody Movimentacao movimentacao) {

    }
}
