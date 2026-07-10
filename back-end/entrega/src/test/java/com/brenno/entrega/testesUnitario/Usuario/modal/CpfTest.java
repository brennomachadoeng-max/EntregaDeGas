package com.brenno.entrega.testesUnitario.Usuario.modal;

import com.brenno.entrega.user.model.Cpf;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CpfTest {

    @Test
    @DisplayName("Deve criar CPF com sucesso quando o formato for válido e com máscara")
    void deveCriarCpfComMascaraComSucesso() {
        // Arrange & Act
        Cpf cpf = new Cpf("123.456.789-01");

        // Assert
        assertNotNull(cpf);
        assertEquals("12345678901", cpf.getNumero(), "O CPF deveria ter apenas os números salvos");
    }

    @Test
    @DisplayName("Deve criar CPF com sucesso quando passar apenas os números válidos")
    void deveCriarCpfApenasNumerosComSucesso() {
        // Arrange & Act
        Cpf cpf = new Cpf("98765432100");

        // Assert
        assertNotNull(cpf);
        assertEquals("98765432100", cpf.getNumero());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123.456.789-0",   // Curto demais (10 dígitos)
            "123.456.789-012", // Longo demais (12 dígitos)
            "",                // Vazio
            "12345",           // Inválido total
            "abc.def.ghi-jk"   // Apenas letras (se DocumentoUtils limpar tudo, vira vazio "")
    })
    @DisplayName("Deve lançar exceção quando o tamanho do CPF for inválido")
    void deveLancarExcecaoQuandoCpfForInvalido(String cpfInvalido) {
        // Act & Assert
        IllegalArgumentException excecao = assertThrows(IllegalArgumentException.class, () -> {
            new Cpf(cpfInvalido);
        });

        assertEquals("CPF inválido", excecao.getMessage());
    }
}
