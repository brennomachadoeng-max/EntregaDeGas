package com.brenno.entrega.logistica.rota.service;

import com.brenno.entrega.logistica.rota.dto.RotaDTO;
import com.brenno.entrega.logistica.rota.dto.RotaRequestDTO;

public interface RotaService {

    RotaDTO calcularRota(RotaRequestDTO request);
}
