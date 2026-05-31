package com.brenno.entrega.controleEstoque.operacaoEntrega.controller;

import com.brenno.entrega.controleEstoque.operacaoEntrega.dto.OperacaoEntregaRequestDTO;
import com.brenno.entrega.controleEstoque.operacaoEntrega.dto.OperacaoEntregaResponseDTO;
import com.brenno.entrega.controleEstoque.operacaoEntrega.service.OperacaoEntregaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operacaoEntrega")
public class OperacaoEntregaController {

    private final OperacaoEntregaService operacaoEntregaService;

    public OperacaoEntregaController(OperacaoEntregaService operacaoEntregaService) {
        this.operacaoEntregaService = operacaoEntregaService;
    }

    @PostMapping
    public ResponseEntity<OperacaoEntregaResponseDTO> cadastrar(@RequestBody OperacaoEntregaRequestDTO operacaoEntregaRequestDTO) {
        OperacaoEntregaResponseDTO operacaoEntregaResponseDTO = operacaoEntregaService.cadastrar(operacaoEntregaRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(operacaoEntregaResponseDTO);
    }
}
