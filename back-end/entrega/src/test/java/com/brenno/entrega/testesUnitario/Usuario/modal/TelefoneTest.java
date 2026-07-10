package com.brenno.entrega.testesUnitario.Usuario.modal;


import com.brenno.entrega.user.model.Telefone;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class TelefoneTest {

    @Test
    @DisplayName("Deve criar Telefone com sucesso quando o formato possuir máscara padrão")
    void deveCriarTelefoneComMascaraComSucesso() {
        Telefone telefone = new Telefone("(75) 99999-9999");

        assertNotNull(telefone);
        assertEquals("75999999999", telefone.getNumero(), "O telefone deveria ter apenas os números salvos");
    }

    @Test
    @DisplayName("Deve criar Telefone com sucesso quando passar apenas os números válidos")
    void deveCriarTelefoneApenasNumerosComSucesso() {
        // Arrange & Act
        Telefone telefone = new Telefone("75999999999");

        // Assert
        assertNotNull(telefone);
        assertEquals("75999999999", telefone.getNumero());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "759999-999",   // Curto demais
            "759999999999", // Longo demais (excesso de dígitos)
            "   ",          // Apenas espaços
            "",             // Vazio
            "abc-def-ghij"  // Apenas letras (ao limpar vira vazio)
    })
    @DisplayName("Deve lançar exceção quando o tamanho do telefone for inválido")
    void deveLancarExcecaoQuandoTelefoneForInvalido(String telefoneInvalido) {
        // Act & Assert
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            new Telefone(telefoneInvalido);
        });

        assertEquals("Telefone inválido.", excecao.getMessage());
    }
}
