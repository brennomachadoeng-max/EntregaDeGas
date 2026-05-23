package com.brenno.entrega.empresa.repository;

import com.brenno.entrega.empresa.model.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    /*
    save(empresa)
    findById(id)
    findAll()
    deleteById(id)
    delete(empresa)
    existsById(id)
    count()
    findAllById(ids)
    */
}
