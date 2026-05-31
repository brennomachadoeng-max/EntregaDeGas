package com.brenno.entrega.controleEstoque.operacaoEntrega.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperacaoEntregaResponseDTO {
    private Integer id;
    private String nomeEntregador;
    private LocalDateTime dataHora;
}
