package com.brenno.entrega.notificacao.solicitacao.dto;

import com.brenno.entrega.notificacao.model.StatusSolicitacao;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class SolicitacaoEntregaResponseDTO {

    private Integer idSolicitacao;
    private Integer idEntregador;
    private String nomeEntregador;
    private StatusSolicitacao status;
    private LocalDateTime dataSolicitacao;
}