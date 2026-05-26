package com.brenno.entrega.logistica.rota.dto;

public record RotaRequestDTO(
        Double origemLatitude,
        Double origemLongitude,
        Double destinoLatitude,
        Double destinoLongitude
) {
}