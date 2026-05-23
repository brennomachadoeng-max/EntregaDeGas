package com.brenno.entrega.produto.statusPedido.service;

import com.brenno.entrega.produto.statusPedido.model.StatusPedido;
import com.brenno.entrega.produto.statusPedido.repository.StatusPedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

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

    public StatusPedido findById(Integer id) {
        return statusPedidoRepository.findById(id).orElseThrow();
    }

    public void delete(StatusPedido statusPedido) {
        statusPedidoRepository.delete(statusPedido);
    }
}
