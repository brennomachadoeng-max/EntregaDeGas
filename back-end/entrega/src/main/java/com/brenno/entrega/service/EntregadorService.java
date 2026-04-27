package com.brenno.entrega.service;

import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.model.Pedido;
import com.brenno.entrega.repository.EntregadorRepository;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregadorService {
    public final EntregadorRepository entregadorRepository;
    public EntregadorService(EntregadorRepository entregadorRepository) {
        this.entregadorRepository = entregadorRepository;
    }

    public Entregador save(Entregador entregador) {
        return entregadorRepository.save(entregador);
    }

    public List<Entregador> findAll() {
        return entregadorRepository.findAll();
    }

    public Optional<Entregador> findById(Integer id) {
        return entregadorRepository.findById(id);
    }

    public void delete(Entregador entregador) {
        entregadorRepository.delete(entregador);
    }

    public Entregador atualizarLocalizcao(Integer id,Entregador dados) {
        Entregador entregador = entregadorRepository.findById(id).orElseThrow(() -> new RuntimeException("Entregador não encontrado"));
        entregador.setLocalizacao(dados.getLocalizacao());
        entregador.setUltimaAtualizacao(java.time.LocalDateTime.now());
        return entregadorRepository.save(entregador);
    }

    public List<Entregador> solicitarEntregador(Pedido pedido) {
        Point localizacaoPedido = pedido.getEndereco().getLocalizacao();
        return entregadorRepository.findEntregadoresProximos(localizacaoPedido, 5000);
    }
}
