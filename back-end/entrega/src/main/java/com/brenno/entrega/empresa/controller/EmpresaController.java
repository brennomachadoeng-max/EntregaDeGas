package com.brenno.entrega.empresa.controller;

import com.brenno.entrega.empresa.dto.EmpresaCadastroDTO;
import com.brenno.entrega.empresa.dto.EmpresaResponseDTO;
import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.empresa.service.EmpresaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<EmpresaResponseDTO> cadastrar(@RequestBody EmpresaCadastroDTO dto) {
        Empresa salvo = empresaService.cadastrar(dto);
        if (salvo == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        EmpresaResponseDTO response = empresaService.EmpresaParaEmpresaResponse(salvo);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
