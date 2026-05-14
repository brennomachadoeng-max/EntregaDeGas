package com.brenno.entrega.service;

import com.brenno.entrega.DTO.entregador.AtualizarLocalizacaoDTO;
import com.brenno.entrega.DTO.entregador.EntregadorCadastroDTO;
import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.model.Pedido;
import com.brenno.entrega.repository.EntregadorRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntregadorService {
    public final EntregadorRepository entregadorRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final GeometryFactory geometryFactory;

    public EntregadorService(EntregadorRepository entregadorRepository, BCryptPasswordEncoder passwordEncoder, GeometryFactory geometryFactory) {
        this.entregadorRepository = entregadorRepository;
        this.passwordEncoder = passwordEncoder;
        this.geometryFactory = geometryFactory;
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

    public Entregador atualizarLocalizacao(Integer id, AtualizarLocalizacaoDTO dto) {
        Entregador entregador = findById(id);
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
        entregador.setLocalizacao(localizacao);
        entregador.setUltimaAtualizacao(java.time.LocalDateTime.now());
        return entregadorRepository.save(entregador);
    }

    public List<Entregador> solicitarEntregador(Pedido pedido) {
        Point localizacaoPedido = pedido.getEndereco().getLocalizacao();
        return entregadorRepository.findEntregadoresProximos(localizacaoPedido, 3000);
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
}
