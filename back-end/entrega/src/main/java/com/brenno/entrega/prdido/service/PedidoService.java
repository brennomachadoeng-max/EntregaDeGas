package com.brenno.entrega.prdido.service;

import com.brenno.entrega.empresa.model.Empresa;
import com.brenno.entrega.prdido.dto.PedidoEntregaResponseDTO;
import com.brenno.entrega.prdido.dto.PedidoRequest;
import com.brenno.entrega.prdido.itemPedido.dto.ProdutoItemPedidoDTO;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.prdido.repository.PedidoRepository;
import com.brenno.entrega.empresa.service.EmpresaService;
import com.brenno.entrega.produto.statusPedido.model.StatusPedido;
import com.brenno.entrega.user.endereco.service.EnderecoService;
import com.brenno.entrega.produto.statusPedido.service.StatusPedidoService;
import com.brenno.entrega.user.endereco.modal.Endereco;
import com.brenno.entrega.user.model.Usuario;
import com.brenno.entrega.user.service.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;
    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final StatusPedidoService statusPedidoService;
    private final EnderecoService enderecoService;
    public PedidoService(PedidoRepository pedidoRepository, UsuarioService usuarioService,EmpresaService empresaService, StatusPedidoService statusPedidoService, EnderecoService enderecoService) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.statusPedidoService = statusPedidoService;
        this.enderecoService = enderecoService;

    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }
    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }
    public Pedido findById(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
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
    public Pedido criarPedido(PedidoRequest request) {
        Usuario usuario = usuarioService.findById(request.getUsuarioId());
        Empresa empresa = empresaService.findById(request.getEmpresaId());
        StatusPedido status = statusPedidoService.findById(request.getStatusId());
        Endereco endereco = enderecoService.findById(request.getEnderecoId());
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setEmpresa(empresa);
        pedido.setStatus(status);
        pedido.setEndereco(endereco);
        return pedidoRepository.save(pedido);
    }
    public PedidoEntregaResponseDTO PedidoParaPedidoEntregaResponseDTO(Pedido pedido, List<ProdutoItemPedidoDTO> produtos) {
        return new PedidoEntregaResponseDTO(
                pedido.getIdPedido(),
                pedido.getUsuario().getNome(),
                pedido.getUsuario().getTelefone(),
                pedido.getEndereco().getRua(),
                pedido.getEndereco().getNumero(),
                pedido.getEndereco().getBairro(),
                pedido.getEndereco().getCidade(),
                pedido.getValorCompra(),
                produtos
        );

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
