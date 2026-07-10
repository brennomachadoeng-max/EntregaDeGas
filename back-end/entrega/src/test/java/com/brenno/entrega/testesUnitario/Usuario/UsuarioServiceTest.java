package com.brenno.entrega.testesUnitario.Usuario;

import com.brenno.entrega.user.dto.UsuarioCadastroDTO;
import com.brenno.entrega.user.dto.UsuarioResponseDTO;
import com.brenno.entrega.user.model.Cpf;
import com.brenno.entrega.user.model.Email;
import com.brenno.entrega.user.model.Telefone;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.repository.UsuarioRepository;
import com.brenno.entrega.user.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    private UsuarioCadastroDTO cadastroDTO;
    private Usuario usuarioSalvo;

    @BeforeEach
    void setUp() {
        cadastroDTO = new UsuarioCadastroDTO(
                "Brenno",
                "12345678901",
                Date.valueOf("2000-01-01"),
                "75999999999",
                "brenno@gmail.com",
                "senha123"
        );

        usuarioSalvo = new Usuario(
                1,
                "Brenno",
                new Cpf("12345678901"),
                Date.valueOf("2000-01-01"),
                new Telefone("75999999999"),
                new Email("brenno@gmail.com"),
                "senhaCriptografada",
                true
        );
    }

    @Test
    @DisplayName("Deve cadastrar um usuário com sucesso criptografando a senha")
    void deveCadastrarUsuarioComSucesso() {
        when(passwordEncoder.encode(cadastroDTO.getSenha())).thenReturn("senhaCriptografada");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuarioSalvo);

        Usuario resultado = usuarioService.cadastrar(cadastroDTO);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdUsuario());
        assertEquals("Brenno", resultado.getNome());
        assertEquals("senhaCriptografada", resultado.getSenha());
    }

    @Test
    @DisplayName("Deve converter UsuarioCadastroDTO para entidade Usuario com sucesso")
    void deveConverterUsuarioCadastroDTOParaUsuarioComSucesso() {
        when(passwordEncoder.encode("senha123")).thenReturn("senhaCriptografadaNoMock");
        Usuario resultado = usuarioService.UsuarioCadastrarDTOParaUsuario(cadastroDTO);

        assertNotNull(resultado);
        assertNull(resultado.getIdUsuario(), "O ID deve ser nulo ao cadastrar"); // Corrigido para getIdUsuario()
        assertEquals("Brenno", resultado.getNome());
        assertEquals("12345678901", resultado.getCpf().getNumero());
        assertEquals("75999999999", resultado.getTelefone().getNumero());
        assertEquals("brenno@gmail.com", resultado.getEmail().getEndereco());
        assertEquals("senhaCriptografadaNoMock", resultado.getSenha());
        assertTrue(resultado.isAtivo(), "O usuário deve nascer como ativo (true)");
    }

    @Test
    @DisplayName("Deve converter Usuario para UsuarioResponseDTO corretamente")
    void deveConverterParaResponseDTO() {
        UsuarioResponseDTO response = usuarioService.UsuarioParaUsuarioResponseDTO(usuarioSalvo);

        assertNotNull(response);
        assertEquals(usuarioSalvo.getIdUsuario(), response.getIdUsuario());
        assertEquals(usuarioSalvo.getNome(), response.getNome());
        assertEquals(usuarioSalvo.getEmail().getEndereco(), response.getEmail());
    }

    @Test
    @DisplayName("Deve validar o login com sucesso quando credenciais forem corretas")
    void deveValidarLoginComSucesso() {
        // Arrange
        String emailBusca = "brenno@gmail.com";
        String senhaDigitada = "senha123";

        when(usuarioRepository.findByEmailEndereco(emailBusca)).thenReturn(usuarioSalvo);
        when(passwordEncoder.matches(senhaDigitada, usuarioSalvo.getSenha())).thenReturn(true);

        // Act
        Usuario resultado = usuarioService.validarLogin(emailBusca, senhaDigitada);

        // Assert
        assertNotNull(resultado);
        assertEquals(emailBusca, resultado.getEmail().getEndereco());
    }

    @Test
    @DisplayName("Deve lancar exceção ao tentar logar com senha incorreta")
    void deveLancarExcecaoQuandoSenhaForIncorreta() {
        // Arrange
        String emailBusca = "brenno@gmail.com";
        String senhaIncorreta = "senhaErrada";

        when(usuarioRepository.findByEmailEndereco(emailBusca)).thenReturn(usuarioSalvo);
        when(passwordEncoder.matches(senhaIncorreta, usuarioSalvo.getSenha())).thenReturn(false);

        // Act & Assert
        RuntimeException excecao = assertThrows(RuntimeException.class, () -> {
            usuarioService.validarLogin(emailBusca, senhaIncorreta);
        });

        assertEquals("Email ou senha inválidos", excecao.getMessage());
    }
}