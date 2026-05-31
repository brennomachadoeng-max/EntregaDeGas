package com.brenno.entrega.controleEstoque.movimentacao.dto;

import com.brenno.entrega.controleEstoque.movimentacao.tipoMovimentacao.TipoMovimentacao;
import com.brenno.entrega.controleEstoque.operacaoEntrega.dto.OperacaoEntregaRequestDTO;
import com.brenno.entrega.controleEstoque.operacaoEntrega.modal.OperacaoEntrega;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MovimentacaoVendaRequestDTO {

    private int idPedido;
    private LocalDateTime dataEntrega;
    private int quantidadeBotijaoCheio;
    private int quantidadeBotijaoVazio;
    private int quantidadeBotijaoCompleto;
    private TipoMovimentacao tipoMovimentacao;
    private OperacaoEntrega operacaoEntrega;
}
