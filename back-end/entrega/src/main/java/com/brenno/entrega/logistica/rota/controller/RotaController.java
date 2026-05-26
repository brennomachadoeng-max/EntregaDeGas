package com.brenno.entrega.logistica.rota.controller;

import com.brenno.entrega.logistica.rota.dto.RotaDTO;
import com.brenno.entrega.logistica.rota.dto.RotaRequestDTO;
import com.brenno.entrega.logistica.rota.service.RotaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rotas")
public class RotaController {

    private final RotaService rotaService;

    public RotaController(RotaService rotaService) {
        this.rotaService = rotaService;
    }

    @PostMapping
    public RotaDTO calcularRota(
            @RequestBody RotaRequestDTO request) {

        return rotaService.calcularRota(request);
    }
}