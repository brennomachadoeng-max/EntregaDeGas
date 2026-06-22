package com.brenno.entrega.user.model;

import com.brenno.entrega.documentoUtils.DocumentoUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Telefone {

    @Column(name = "telefone", nullable = false, unique = true)
    private String numero;

    public Telefone(String telefoneBruto) {

        String telefone = DocumentoUtils.somenteNumeros(telefoneBruto);

        if (!DocumentoUtils.possuiTamanhoTelefone(telefone)) {
            throw new IllegalArgumentException("Telefone inválido.");
        }

        this.numero = telefone;
    }
}
