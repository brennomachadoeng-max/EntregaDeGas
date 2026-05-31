package com.brenno.entrega.controleEstoque.movimentacao.dto;

import com.brenno.entrega.controleEstoque.operacaoEntrega.modal.OperacaoEntrega;
import lombok.Data;

@Data
public class MovimentacaoCargaInicialRuequestDTO {
   private int quantidadeBotijaoCheio;
   private OperacaoEntrega operacaoEntrega;
}
