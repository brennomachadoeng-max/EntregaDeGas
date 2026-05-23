package com.brenno.entrega.empresa.service;

import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.empresa.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    public final EmpresaRepository empresaRepository;
    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    public List<Empresa> findAll() {
        return empresaRepository.findAll();
    }

    public Empresa findById(Integer id) {
        return empresaRepository.findById(id).orElseThrow();
    }

    public void delete(Empresa empresa) {
        empresaRepository.delete(empresa);
    }
}
