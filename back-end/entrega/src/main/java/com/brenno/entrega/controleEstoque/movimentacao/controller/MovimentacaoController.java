package com.brenno.entrega.controleEstoque.movimentacao.controller;

import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoCargaInicialRuequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoReabastecerRequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentacaoVendaRequestDTO;
import com.brenno.entrega.controleEstoque.movimentacao.dto.MovimentcaoResponseDTO;
import com.brenno.entrega.controleEstoque.movimentacao.service.MovimentacaoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {

    private final MovimentacaoService movimentacaoService;

    public MovimentacaoController(MovimentacaoService movimentacaoService) {
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping("/carga")
    public ResponseEntity<MovimentcaoResponseDTO> carga(@RequestBody MovimentacaoCargaInicialRuequestDTO movimentacaoCargaInicialRuequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.cargaInicial(movimentacaoCargaInicialRuequestDTO));
    }

    @PostMapping("/reabastecer")
    public ResponseEntity<MovimentcaoResponseDTO> carga(@RequestBody MovimentacaoReabastecerRequestDTO movimentacaoReabastecerRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.reabastecer(movimentacaoReabastecerRequestDTO));
    }

    @PostMapping("/venda")
    public ResponseEntity<MovimentcaoResponseDTO> venda(@RequestBody MovimentacaoVendaRequestDTO movimentacaoVendaRequestDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(movimentacaoService.venda(movimentacaoVendaRequestDTO));
    }
}
