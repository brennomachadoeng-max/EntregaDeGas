package com.brenno.entrega.controleEstoque.movimentacao.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoReabastecerRequestDTO {

    private int idEntregador;
    private LocalDateTime dataEntrega;
    private int quantidadeBotijaoCheio;
    private int quantidadeBotijaoVazio;
}
