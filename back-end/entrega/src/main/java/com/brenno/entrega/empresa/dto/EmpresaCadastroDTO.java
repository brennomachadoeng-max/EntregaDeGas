package com.brenno.entrega.empresa.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class EmpresaCadastroDTO {

    private String razaoSocial;
    private String cnpj;
    private String telefone;

    private String rua;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;
    private Double latitude;
    private Double longitude;

    @Override
    public String toString() {
        return String.format(
                "%s, %s, %s, %s, %s",
                rua,
                numero,
                bairro,
                cidade,
                cep
        );
    }
}