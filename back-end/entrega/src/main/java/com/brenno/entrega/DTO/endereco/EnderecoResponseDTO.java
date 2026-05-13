package com.brenno.entrega.DTO.endereco;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnderecoResponseDTO {
    private Integer idEndereco;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
}
