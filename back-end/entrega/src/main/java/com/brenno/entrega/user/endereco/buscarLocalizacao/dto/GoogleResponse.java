package com.brenno.entrega.user.endereco.buscarLocalizacao.dto;
import lombok.Data;
import java.util.List;

@Data
public class GoogleResponse {
    private List<Result> results;
}
