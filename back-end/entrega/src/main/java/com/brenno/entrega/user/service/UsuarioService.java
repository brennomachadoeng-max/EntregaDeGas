package com.brenno.entrega.user.service;

import com.brenno.entrega.user.dto.UsuarioCadastroDTO;
import com.brenno.entrega.user.dto.UsuarioResponseDTO;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,  BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(UsuarioCadastroDTO dto) {
        Usuario usuario = UsuarioCadastrarDTOParaUsuario(dto);
        return usuarioRepository.save(usuario);
    }

    public Usuario UsuarioCadastrarDTOParaUsuario(UsuarioCadastroDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setCpf(dto.getCpf());
        usuario.setDataNascimento(dto.getDataNascimento());
        usuario.setTelefone(dto.getTelefone());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setAtivo(true);
        return usuario;
    }

    public UsuarioResponseDTO UsuarioParaUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail());
    }

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    public void delete(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }

    public Optional<Usuario> validarLogin(String email, String senha) {
        return usuarioRepository.findByEmail(email).filter(usuario -> passwordEncoder.matches(senha, usuario.getSenha()));
    }
}
