package com.brenno.entrega.user.endereco.service;

import com.brenno.entrega.buscarLocalizacao.service.BuscarLocalizacaoService;
import com.brenno.entrega.user.endereco.dto.EnderecoRequestDTO;
import com.brenno.entrega.user.endereco.dto.EnderecoResponseDTO;
import com.brenno.entrega.user.endereco.modal.Endereco;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.endereco.repository.EnderecoRepository;
import com.brenno.entrega.user.repository.UsuarioRepository;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final GeometryFactory geometryFactory;
    private final BuscarLocalizacaoService buscarLocalizacaoService;


    public EnderecoService(
            EnderecoRepository enderecoRepository,
            UsuarioRepository usuarioRepository,
            GeometryFactory geometryFactory,
            BuscarLocalizacaoService buscarLocalizacaoService
    ) {
        this.buscarLocalizacaoService = buscarLocalizacaoService;
        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.geometryFactory = geometryFactory;
    }
    public Endereco cadastrar(EnderecoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        dto = buscarLocalizacaoService.buscarLocalizacao(dto);
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
        Endereco endereco = EnderecoRequestDTOParaEndereco(dto, usuario, localizacao);
        return enderecoRepository.save(endereco);
    }
    private Endereco EnderecoRequestDTOParaEndereco(EnderecoRequestDTO dto, Usuario usuario, Point localizacao ) {
        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario);
        endereco.setRua(dto.getRua());
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setBairro(dto.getBairro());
        endereco.setCidade(dto.getCidade());
        endereco.setEstado(dto.getEstado());
        endereco.setCep(dto.getCep());
        endereco.setLocalizacao(localizacao);
        return endereco;
    }

    public EnderecoResponseDTO EnderecoParaEndercoResponseDTO(Endereco endereco) {
        return new EnderecoResponseDTO(endereco.getIdEndereco(), endereco.getRua(), endereco.getNumero(), endereco.getBairro(), endereco.getCidade(), endereco.getEstado());
    }
    public List<Endereco> findByUsuario(Integer usuarioId) {
        return enderecoRepository.findByUsuarioIdUsuario(usuarioId);
    }
    public Endereco save(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }
    public List<Endereco> findAll() {
        return enderecoRepository.findAll();
    }
    public Endereco findById(Integer id) {
        return enderecoRepository.findById(id).orElseThrow();
    }
    public void delete(Endereco endereco) {
        enderecoRepository.delete(endereco);
    }
    public List<Endereco> findByUsuario(Usuario usuario) {
        return enderecoRepository.findByUsuario(usuario);
    }
}
