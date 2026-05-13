package com.brenno.entrega.Controller;

import com.brenno.entrega.DTO.PedidoProdutoRequest;
import com.brenno.entrega.DTO.PedidoRequest;
import com.brenno.entrega.model.*;
import com.brenno.entrega.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final StatusPedidoService statusPedidoService;
    private final EnderecoService enderecoService;
    private final PedidoProdutoService pedidoProdutoService;
    private final ProdutoService produtoService;

    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService,
                            EmpresaService empresaService, StatusPedidoService statusPedidoService,
                            EnderecoService enderecoService,  PedidoProdutoService pedidoProdutoService,
                            ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.statusPedidoService = statusPedidoService;
        this.enderecoService = enderecoService;
        this.pedidoProdutoService = pedidoProdutoService;
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = CriandoPedido(pedidoRequest);
        AdicionandoProdutoAoPedido(pedido, pedidoRequest.getProdutos());
        return ResponseEntity.ok(pedido);
    }

    private void AdicionandoProdutoAoPedido(Pedido pedido, List<PedidoProdutoRequest> produtos) {
        produtos.forEach(item -> {
            Produto produto = produtoService.findById(item.getProdutoId()).orElseThrow();
            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);
            pedidoProduto.setQuantidade(item.getQuantidade());
            pedidoProduto.setValorUnitario(produto.getValor());
            pedidoProduto.setDesconto(item.getDesconto());
            pedidoProdutoService.save(pedidoProduto);
        });
    }
    private Pedido CriandoPedido(PedidoRequest pedidoRequest){
        Optional<Usuario> usuario = usuarioService.findById(pedidoRequest.getUsuarioId());
        Optional<Empresa> empresa = empresaService.findById(pedidoRequest.getEmpresaId());
        Optional<StatusPedido> statusPedido = statusPedidoService.findById(pedidoRequest.getStatusId());
        Optional<Endereco> endereco = enderecoService.findById(pedidoRequest.getEnderecoId());
        return pedidoService.aberturaPedido(usuario.get(), empresa.get(), statusPedido.get(), endereco.get());
    }

}
