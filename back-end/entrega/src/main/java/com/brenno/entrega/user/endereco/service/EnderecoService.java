package com.brenno.entrega.user.endereco.service;

import com.brenno.entrega.user.endereco.dto.EnderecoRequestDTO;
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

    public EnderecoService(
            EnderecoRepository enderecoRepository,
            UsuarioRepository usuarioRepository,
            GeometryFactory geometryFactory) {

        this.enderecoRepository = enderecoRepository;
        this.usuarioRepository = usuarioRepository;
        this.geometryFactory = geometryFactory;
    }


    public Endereco cadastrar(EnderecoRequestDTO dto) {
        Usuario usuario = usuarioRepository.findById(dto.getUsuarioId()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
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
        return enderecoRepository.save(endereco);
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
