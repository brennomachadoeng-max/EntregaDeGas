package com.brenno.entrega.repository;

import com.brenno.entrega.model.Endereco;
import com.brenno.entrega.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
    /*
    save(endereco)
    findById(id)
    findAll()
    deleteById(id)
    delete(endereco)
    existsById(id)
    count()
    findAllById(ids)
    */
    List<Endereco> findByUsuarioIdUsuario(Integer idUsuario);
    List<Endereco> findByUsuario(Usuario usuario);
}
