package com.brenno.entrega.auth.modalLogin;

import com.brenno.entrega.auth.dto.LoginResponse;
import com.brenno.entrega.auth.interfac.LoginProvider;
import com.brenno.entrega.documentoUtils.DocumentoUtils;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.entregador.service.EntregadorService;
import org.springframework.stereotype.Component;

@Component
public class EntregadorLoginProvider implements LoginProvider {

    private final EntregadorService entregadorService;

    public EntregadorLoginProvider(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;
    }

    @Override
    public boolean suporta(String login) {
        login = DocumentoUtils.somenteNumeros(login);
        return DocumentoUtils.possuiTamanhoCpf(login);
    }

    @Override
    public LoginResponse autenticar(String login, String senha) {
        Entregador entregador = entregadorService.validarLogin(login, senha);
        return new LoginResponse(
                entregador.getIdEntregador(),
                entregador.getNome(),
                entregador.getTelefone(),
                "ENTREGADOR"
        );
    }
}