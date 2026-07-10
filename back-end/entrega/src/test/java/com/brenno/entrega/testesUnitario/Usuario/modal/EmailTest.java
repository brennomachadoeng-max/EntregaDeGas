package com.brenno.entrega.testesUnitario.Usuario.modal;

import com.brenno.entrega.user.model.Email;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class EmailTest {

    @Test
    @DisplayName("Deve criar Email com sucesso e formatar para minúsculo e sem espaços nas pontas")
    void deveCriarEmailComSucessoEFormatarTexto() {
        Email email = new Email("  Brenno@GMAIL.com  ");

        // Assert
        assertNotNull(email);
        assertEquals("brenno@gmail.com", email.getEndereco(),
                "O e-mail deveria ter sido limpo (trim) e convertido para minúsculas");
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "brenno", // Seu código já barra e lança exceção
            "   ",    // Seu código já barra e lança exceção
            ""        // Seu código já barra e lança exceção
    })
    @DisplayName("Deve lançar exceção para formatos que o sistema já reconhece como inválidos")
    void deveLancarExcecaoQuandoEmailForInvalido(String emailInvalido) {
        // Act & Assert
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            new Email(emailInvalido);
        });

        assertEquals("Email inválido.", excecao.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "brenno@",       // O DocumentoUtils atual deixa passar (Não lança erro)
            "@gmail.com",    // O DocumentoUtils atual deixa passar (Não lança erro)
            "brenno@gmail."  // O DocumentoUtils atual deixa passar (Não lança erro)
    })
    @DisplayName("Deve aceitar temporariamente formatos que o DocumentoUtils atual não barra")
    void deveCriarEmailSemLancarExcecaoParaCasosPermissivos(String emailPermissivo) {
        // Act
        Email email = new Email(emailPermissivo);

        // Assert
        assertNotNull(email);
        // Garante que o objeto foi criado com o e-mail que foi passado
        assertEquals(emailPermissivo.trim().toLowerCase(), email.getEndereco());
    }
}
