package com.brenno.entrega.entregador.service;

import com.brenno.entrega.entregador.dto.EntregadorCadastroDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.entregador.repository.EntregadorRepository;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
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

    public Entregador cadastrar(EntregadorCadastroDTO dto) {
        Entregador entregador = new Entregador();
        entregador.setNome(dto.getNome());
        entregador.setCpf(dto.getCpf());
        entregador.setTelefone(dto.getTelefone());
        entregador.setSenha(passwordEncoder.encode(dto.getSenha()));
        entregador.setAtivo(true);
        return entregadorRepository.save(entregador);
    }

    public Entregador validarLogin(String cpf, String senha) {
        return entregadorRepository.findByCpf(cpf).filter(entregador -> passwordEncoder.matches(senha, entregador.getSenha())).orElseThrow(() -> new RuntimeException("CPF ou senha inválidos"));
    }
}
