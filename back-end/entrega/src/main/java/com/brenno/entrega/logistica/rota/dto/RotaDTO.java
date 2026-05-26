package com.brenno.entrega.logistica.rota.dto;

public record RotaDTO(
        String polyline,
        Integer distanciaMetros,
        Integer duracaoSegundos
) {
}