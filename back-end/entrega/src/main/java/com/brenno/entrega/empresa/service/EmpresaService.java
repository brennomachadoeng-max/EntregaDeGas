package com.brenno.entrega.empresa.service;

import com.brenno.entrega.buscarLocalizacao.service.BuscarLocalizacaoService;
import com.brenno.entrega.empresa.dto.EmpresaCadastroDTO;
import com.brenno.entrega.empresa.dto.EmpresaResponseDTO;
import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.empresa.repository.EmpresaRepository;
import com.brenno.entrega.user.endereco.modal.Endereco;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpresaService {
    public final EmpresaRepository empresaRepository;
    public final GeometryFactory geometryFactory;
    public final BuscarLocalizacaoService buscarLocalizacaoService;
    public EmpresaService(EmpresaRepository empresaRepository,BuscarLocalizacaoService buscarLocalizacaoService, GeometryFactory geometryFactory) {
        this.empresaRepository = empresaRepository;
        this.buscarLocalizacaoService = buscarLocalizacaoService;
        this.geometryFactory = geometryFactory;

    }

    public Empresa cadastrar(EmpresaCadastroDTO dto){
        dto = buscarLocalizacaoService.buscarLocalizacaoEmpresa(dto);
        Point localizacao = geometryFactory.createPoint(new Coordinate(dto.getLongitude(), dto.getLatitude()));
        Empresa empresa = EmpresaCadastrarParaEmpresa(dto, localizacao);
        return empresaRepository.save(empresa);
    }
    public EmpresaResponseDTO EmpresaParaEmpresaResponse(Empresa empresa) {
        EmpresaResponseDTO empresaResponseDTO = new EmpresaResponseDTO();
        empresaResponseDTO.setIdEmpresa(empresa.getIdEmpresa());
        empresaResponseDTO.setRazaoSocial(empresa.getRazaoSocial());
        empresaResponseDTO.setTelefone(empresa.getTelefone());
        return empresaResponseDTO;
    }
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
    public Empresa EmpresaCadastrarParaEmpresa(EmpresaCadastroDTO dto, Point localizacao) {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial(dto.getRazaoSocial());
        empresa.setCnpj(dto.getCnpj());
        empresa.setTelefone(dto.getTelefone());
        empresa.setLocalizacao(localizacao);
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
