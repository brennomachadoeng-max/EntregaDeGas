package com.brenno.entrega.entregador.service;

import com.brenno.entrega.documentoUtils.DocumentoUtils;
import com.brenno.entrega.entregador.dto.EntregadorCadastroDTO;
import com.brenno.entrega.entregador.dto.EntregadorResponseDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.entregador.repository.EntregadorRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntregadorService {
    public final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public EntregadorService(EntregadorRepository entregadorRepository, BCryptPasswordEncoder passwordEncoder) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public Entregador save(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }

    public List<Entregador> findAll() {
        return entregadorRepository.findAll();
    }

    public Entregador findById(Integer id) {
        return entregadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
    }

    public void delete(Entregador entregador) {
        entregadorRepository.delete(entregador);
    }

    public EntregadorResponseDTO cadastrar(EntregadorCadastroDTO dto) {
        dto.setTelefone(DocumentoUtils.somenteNumeros(dto.getTelefone()));
        dto.setCpf(DocumentoUtils.somenteNumeros(dto.getCpf()));
        if(DocumentoUtils.possuiTamanhoCpf(dto.getCpf()) && DocumentoUtils.possuiTamanhoTelefone(dto.getTelefone())) {
            Entregador entregador = EntregadorCadastrarDTOParaEntregador(dto);
            return EntregadorParaEntregadorResponseDTO(entregadorRepository.save(entregador));
        }
        return null;
    }

    public Entregador EntregadorCadastrarDTOParaEntregador(EntregadorCadastroDTO dto) {
        return new Entregador(dto.getNome(), dto.getCpf(), dto.getTelefone(), passwordEncoder.encode(dto.getSenha()), true);
    }

    public EntregadorResponseDTO EntregadorParaEntregadorResponseDTO(Entregador entregador) {
        return new EntregadorResponseDTO(entregador.getIdEntregador(), entregador.getNome(), entregador.getTelefone(), entregador.getAtivo());
    }

    public Entregador validarLogin(String cpf, String senha) {
        return entregadorRepository.findByCpf(cpf).filter(entregador -> passwordEncoder.matches(senha, entregador.getSenha())).orElseThrow(() -> new RuntimeException("CPF ou senha inválidos"));
    }

    public EntregadorResponseDTO alternarStatusOnline(Integer id) {
        Entregador entregador = findById(id);
        entregador.setAtivo(!Boolean.TRUE.equals(entregador.getAtivo()));
        return EntregadorParaEntregadorResponseDTO(entregadorRepository.save(entregador));
    }
}
