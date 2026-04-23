package com.brenno.entrega.service;

import com.brenno.entrega.model.Endereco;
import com.brenno.entrega.model.Usuario;
import com.brenno.entrega.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    public final EnderecoRepository enderecoRepository;
    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Integer id) {
        return enderecoRepository.findById(id);
    }

    public void delete(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }

    public List<Endereco> findByUsuario(Usuario usuario) {
        return enderecoRepository.findByUsuario(usuario);
    }
}
