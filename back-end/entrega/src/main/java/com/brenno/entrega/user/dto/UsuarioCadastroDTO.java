package com.brenno.entrega.user.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class UsuarioCadastroDTO {

    private String nome;
    private String cpf;
    private Date dataNascimento;
    private String telefone;
    private String email;
    private String senha;

}