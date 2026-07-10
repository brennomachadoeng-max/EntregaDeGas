package com.brenno.entrega.user.service;

import com.brenno.entrega.user.dto.UsuarioCadastroDTO;
import com.brenno.entrega.user.dto.UsuarioResponseDTO;
import com.brenno.entrega.user.model.Cpf;
import com.brenno.entrega.user.model.Email;
import com.brenno.entrega.user.model.Telefone;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return new Usuario(
                null,
                dto.getNome(),
                new Cpf(dto.getCpf()),
                dto.getDataNascimento(),
                new Telefone(dto.getTelefone()),
                new Email(dto.getEmail()),
                passwordEncoder.encode(dto.getSenha()),
                true
        );
    }

    public UsuarioResponseDTO UsuarioParaUsuarioResponseDTO(Usuario usuario) {
        return new UsuarioResponseDTO(usuario.getIdUsuario(), usuario.getNome(), usuario.getEmail().getEndereco());
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

    public Usuario validarLogin(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmailEndereco(email);
        if (usuario == null || !passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }
        return usuario;
    }
}
