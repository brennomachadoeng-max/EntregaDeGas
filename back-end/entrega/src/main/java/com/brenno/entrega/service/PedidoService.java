package com.brenno.entrega.service;

import com.brenno.entrega.model.*;
import com.brenno.entrega.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Integer id) {
        return pedidoRepository.findById(id);
    }

    public void delete(Pedido pedido) {
        pedidoRepository.delete(pedido);
    }

    public Pedido aberturaPedido(Usuario usuario, Empresa empresa, StatusPedido statusPedido, Endereco endereco) {
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setStatus(statusPedido);
        pedido.setEndereco(endereco);
        return save(pedido);
    }

    @Transactional
    public Pedido aceitarPedido(Integer pedidoId, Integer entregadorId) {

        int updated = pedidoRepository.aceitarPedido(pedidoId, entregadorId);

        if (updated == 0) {
            throw new RuntimeException("Pedido já foi aceito por outro entregador");
        }

        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }
}
