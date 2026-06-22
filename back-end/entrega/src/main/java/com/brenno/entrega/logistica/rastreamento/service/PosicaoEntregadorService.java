package com.brenno.entrega.logistica.rastreamento.service;
import com.brenno.entrega.logistica.rastreamento.dto.AtualizarLocalizacaoDTO;
import com.brenno.entrega.entregador.model.Entregador;
import com.brenno.entrega.entregador.service.EntregadorService;
import com.brenno.entrega.logistica.rastreamento.model.PosicaoEntregador;
import com.brenno.entrega.logistica.rastreamento.repository.PosicaoEntregadorRepository;
import com.brenno.entrega.pedido.model.Pedido;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PosicaoEntregadorService {
    PosicaoEntregadorRepository repository;
    public final EntregadorService enteregadorService;
    private final GeometryFactory geometryFactory;

    public PosicaoEntregadorService(PosicaoEntregadorRepository repository,EntregadorService enteregadorService, GeometryFactory geometryFactory) {
        this.repository = repository;
        this.enteregadorService = enteregadorService;
        this.geometryFactory = geometryFactory;
    }
    public PosicaoEntregador atualizarLocalizacao(Integer id, AtualizarLocalizacaoDTO dto) {
        Entregador entregador = enteregadorService.findById(id);
        PosicaoEntregador posicao = buscarEntregadorPosicao(entregador);
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
        posicao.setLocalizacao(localizacao);
        posicao.setUltimaAtualizacao(java.time.LocalDateTime.now());
        return save(posicao);
    }
    public PosicaoEntregador buscarEntregadorPosicao(Entregador entregador) {
        return repository.findByEntregador(entregador).orElseGet(() -> criar(entregador));
    }
    public PosicaoEntregador criar(Entregador entregador) {
        PosicaoEntregador posicao = new PosicaoEntregador();
        posicao.setEntregador(entregador);
        return repository.save(posicao);
    }
    public PosicaoEntregador save(PosicaoEntregador posicaoEntregador) {
        return repository.save(posicaoEntregador);
    }
    public List<Entregador> buscarEntregadorProximo(Pedido pedido, double raio) {
        Point localizacaoPedido = pedido.getEndereco().getLocalizacao();
        List<PosicaoEntregador> posicaoEntregadors = repository.findEntregadoresProximos(localizacaoPedido, raio);
        return posicaoEntregadors.stream().map(PosicaoEntregador::getEntregador).toList();
    }
}
