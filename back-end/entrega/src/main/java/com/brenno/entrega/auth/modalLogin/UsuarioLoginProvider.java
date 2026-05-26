package com.brenno.entrega.auth.modalLogin;

import com.brenno.entrega.auth.dto.LoginResponse;
import com.brenno.entrega.auth.interfac.LoginProvider;
import com.brenno.entrega.documentoUtils.DocumentoUtils;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.service.UsuarioService;
import org.springframework.stereotype.Component;

@Component
public class UsuarioLoginProvider implements LoginProvider {

    private final UsuarioService usuarioService;

    public UsuarioLoginProvider(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Override
    public boolean suporta(String login) {
        return DocumentoUtils.verificarEmail(login);
    }

    @Override
    public LoginResponse autenticar(String login, String senha) {
        Usuario usuario = usuarioService.validarLogin(login, senha);
        return new LoginResponse(
                usuario.getIdUsuario(),
                usuario.getNome(),
                usuario.getEmail(),
                "USUARIO"
        );
    }
}
