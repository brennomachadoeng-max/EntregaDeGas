package com.brenno.entrega.user.model;

import com.brenno.entrega.documentoUtils.DocumentoUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Cpf {

    @Column(name = "cpf")
    private String numero;

    protected Cpf() {}

    public Cpf(String cpfBruto) {

        String cpf = DocumentoUtils.somenteNumeros(cpfBruto);

        if (!DocumentoUtils.possuiTamanhoCpf(cpf)) {
            throw new IllegalArgumentException("CPF inválido");
        }

        this.numero = cpf;
    }
}
