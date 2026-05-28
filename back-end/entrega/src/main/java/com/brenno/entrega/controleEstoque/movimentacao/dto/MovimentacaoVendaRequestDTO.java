package com.brenno.entrega.controleEstoque.movimentacao.dto;

import com.brenno.entrega.controleEstoque.movimentacao.tipoMovimentacao.TipoMovimentacao;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoVendaRequestDTO {

    private int idEntregador;
    private int idPedido;
    private LocalDateTime dataEntrega;
    private int quantidadeBotijaoCheio;
    private int quantidadeBotijaoVazio;
    private int quantidadeBotijaoCompleto;
    private TipoMovimentacao tipoMovimentacao;
}
