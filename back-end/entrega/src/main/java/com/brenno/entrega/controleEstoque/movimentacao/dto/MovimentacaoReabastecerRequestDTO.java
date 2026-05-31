package com.brenno.entrega.controleEstoque.movimentacao.dto;

import com.brenno.entrega.controleEstoque.operacaoEntrega.modal.OperacaoEntrega;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoReabastecerRequestDTO {

    private LocalDateTime dataEntrega;
    private int quantidadeBotijaoCheio;
    private int quantidadeBotijaoVazio;
    private OperacaoEntrega operacaoEntrega;
}
