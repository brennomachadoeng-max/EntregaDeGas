package com.brenno.entrega.user.repository;

import com.brenno.entrega.user.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /*
    save(usuario)
    findById(id)
    findAll()
    deleteById(id)
    delete(usuario)
    existsById(id)
    count()
    findAllById(ids)
    */
    Optional<Usuario> findByEmail(String email);
}
