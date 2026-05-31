package com.brenno.entrega.controleEstoque.operacaoEntrega.service;

import com.brenno.entrega.controleEstoque.operacaoEntrega.dto.OperacaoEntregaRequestDTO;
import com.brenno.entrega.controleEstoque.operacaoEntrega.dto.OperacaoEntregaResponseDTO;
import com.brenno.entrega.controleEstoque.operacaoEntrega.modal.OperacaoEntrega;
import com.brenno.entrega.controleEstoque.operacaoEntrega.repository.OperecaoEntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OperacaoEntregaService {

    private final OperecaoEntregaRepository  operacaoEntregaRepository;

    public OperacaoEntregaService(OperecaoEntregaRepository operacaoEntregaRepository) {
        this.operacaoEntregaRepository = operacaoEntregaRepository;
    }

    public OperacaoEntregaResponseDTO cadastrar(OperacaoEntregaRequestDTO operacaoEntregaRequestDTO) {
        OperacaoEntrega operacaoEntrega = OperacaoEntregaRequestDTOParaOperacaoEntrega(operacaoEntregaRequestDTO);
        return OperacaoEntregaParaOperacaoEntregaResponseDTO(operacaoEntregaRepository.save(operacaoEntrega));
    }

    public OperacaoEntrega OperacaoEntregaRequestDTOParaOperacaoEntrega(OperacaoEntregaRequestDTO operacaoEntregaRequestDTO) {
        OperacaoEntrega operacaoEntrega = new OperacaoEntrega();
        operacaoEntrega.setEntregador(operacaoEntregaRequestDTO.getEntregador());
        operacaoEntrega.setDataHora(LocalDateTime.now());
        return operacaoEntrega;
    }

    public OperacaoEntregaResponseDTO OperacaoEntregaParaOperacaoEntregaResponseDTO(OperacaoEntrega operacaoEntrega) {
        OperacaoEntregaResponseDTO operacaoEntregaResponseDTO = new OperacaoEntregaResponseDTO();
        operacaoEntregaResponseDTO.setId(operacaoEntrega.getIdOperacaoEntrega());
        operacaoEntregaResponseDTO.setNomeEntregador(operacaoEntrega.getEntregador().getNome());
        operacaoEntregaResponseDTO.setDataHora(operacaoEntrega.getDataHora());
        return operacaoEntregaResponseDTO;
    }
}
