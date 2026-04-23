package com.brenno.entrega.service;

import com.brenno.entrega.model.StatusPedido;
import com.brenno.entrega.repository.StatusPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusPedidoService {
    public final StatusPedidoRepository statusPedidoRepository;
    public StatusPedidoService(StatusPedidoRepository statusPedidoRepository) {
        this.statusPedidoRepository = statusPedidoRepository;
    }

    public StatusPedido save(StatusPedido statusPedido) {
        return statusPedidoRepository.save(statusPedido);
    }

    public List<StatusPedido> findAll() {
        return statusPedidoRepository.findAll();
    }

    public Optional<StatusPedido> findById(Integer id) {
        return statusPedidoRepository.findById(id);
    }

    public void delete(StatusPedido statusPedido) {
        statusPedidoRepository.delete(statusPedido);
    }
}
