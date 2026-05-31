package com.brenno.entrega.controleEstoque.operacaoEntrega.dto;

import com.brenno.entrega.entregador.model.Entregador;
import lombok.Data;

@Data
public class OperacaoEntregaRequestDTO {
    private Entregador entregador;
}
