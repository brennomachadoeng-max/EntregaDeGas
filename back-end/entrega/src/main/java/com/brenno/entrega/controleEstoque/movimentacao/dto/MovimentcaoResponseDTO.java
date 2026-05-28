package com.brenno.entrega.controleEstoque.movimentacao.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentcaoResponseDTO {
    private int idEntregador;
    private int idPedido;
    private LocalDateTime dataEntrega;
    private int quantidadeBotijaoCheio;
    private int quantidadeBotijaoVazio;
    private int quantidadeBotijaoCompleto;
    private String tipoMovimentacao;

}
