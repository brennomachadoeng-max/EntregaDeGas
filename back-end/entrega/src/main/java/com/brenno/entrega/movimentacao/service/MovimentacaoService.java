package com.brenno.entrega.movimentacao.service;

import com.brenno.entrega.movimentacao.model.Movimentacao;
import com.brenno.entrega.movimentacao.repository.MovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovimentacaoService {
    private final MovimentacaoRepository movimentacaoRepository;
    public MovimentacaoService(MovimentacaoRepository movimentacaoRepository) {
        this.movimentacaoRepository = movimentacaoRepository;
    }

    public Movimentacao save(Movimentacao movimentacao) {
        return movimentacaoRepository.save(movimentacao);
    }

    public List<Movimentacao> findAll() {
        return movimentacaoRepository.findAll();
    }

    public Optional<Movimentacao> findById(Integer id) {
        return movimentacaoRepository.findById(id);
    }

    public void delete(Movimentacao movimentacao) {
        movimentacaoRepository.delete(movimentacao);
    }
}
