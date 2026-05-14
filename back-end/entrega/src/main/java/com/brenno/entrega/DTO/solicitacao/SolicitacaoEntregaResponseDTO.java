package com.brenno.entrega.DTO.solicitacao;

import com.brenno.entrega.model.StatusSolicitacao;
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