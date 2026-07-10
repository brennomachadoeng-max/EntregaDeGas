package com.brenno.entrega.testesUnitario.Usuario;

import com.brenno.entrega.user.model.Cpf;
import com.brenno.entrega.user.model.Email;
import com.brenno.entrega.user.model.Telefone;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.sql.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // <-- ADICIONE ISTO
public class UsuarioRepositoryTest {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioRepositoryTest(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Test
    void deveBuscarUsuarioPorEmail() {
        Usuario usuario = new Usuario(
                null,
                "Brenno",
                new Cpf("123.456.789-01"),
                Date.valueOf("2000-01-01"),
                new Telefone("(75) 99999-9999"),
                new Email("brenno@gmail.com"),
                "senhaCriptografada",
                true
        );
        usuarioRepository.save(usuario);
        Usuario resposta = usuarioRepository.findByEmailEndereco("brenno@gmail.com");
        assertEquals("Brenno", resposta.getNome());
    }
}
