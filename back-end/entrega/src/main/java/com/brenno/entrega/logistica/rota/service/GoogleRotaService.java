package com.brenno.entrega.logistica.rota.service;

import com.brenno.entrega.logistica.rota.client.GoogleRoutesClient;
import com.brenno.entrega.logistica.rota.dto.RotaDTO;
import com.brenno.entrega.logistica.rota.dto.RotaRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class GoogleRotaService implements RotaService {

    private final GoogleRoutesClient client;

    public GoogleRotaService(
            GoogleRoutesClient client) {
        this.client = client;
    }

    @Override
    public RotaDTO calcularRota(
            RotaRequestDTO request) {

        return client.calcularRota(request);
    }
}
