package com.brenno.entrega.movimentacao.tipoMovimentacao.service;

import com.brenno.entrega.movimentacao.tipoMovimentacao.model.TipoMovimentacao;
import com.brenno.entrega.movimentacao.tipoMovimentacao.repository.TipoMovimentacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TipoMovimentacaoService {

    public final TipoMovimentacaoRepository tipoMovimentacaoRepository;
    public TipoMovimentacaoService(TipoMovimentacaoRepository tipoMovimentacaoRepository) {
        this.tipoMovimentacaoRepository = tipoMovimentacaoRepository;
    }

    public TipoMovimentacao save(TipoMovimentacao tipoMovimentacao) {
        return tipoMovimentacaoRepository.save(tipoMovimentacao);
    }

    public List<TipoMovimentacao> findAll() {
        return tipoMovimentacaoRepository.findAll();
    }

    public Optional<TipoMovimentacao> findById(Integer id) {
        return tipoMovimentacaoRepository.findById(id);
    }

    public void delete(TipoMovimentacao tipoMovimentacao) {
        tipoMovimentacaoRepository.delete(tipoMovimentacao);
    }
}
