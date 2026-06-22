package com.brenno.entrega.user.model;

import com.brenno.entrega.documentoUtils.DocumentoUtils;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Email {

    @Column(name = "email", nullable = false, unique = true)
    private String endereco;

    public Email(String email) {

        if (!DocumentoUtils.verificarEmail(email)) {
            throw new IllegalArgumentException("Email inválido.");
        }

        this.endereco = email.trim().toLowerCase();
    }
}
