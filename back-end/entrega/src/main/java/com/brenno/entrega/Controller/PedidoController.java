package com.brenno.entrega.Controller;

import com.brenno.entrega.DTO.PedidoRequest;
import com.brenno.entrega.model.*;
import com.brenno.entrega.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;
    private final UsuarioService usuarioService;
    private final EmpresaService empresaService;
    private final StatusPedidoService statusPedidoService;
    private final EnderecoService enderecoService;

    public PedidoController(PedidoService pedidoService, UsuarioService usuarioService, EmpresaService empresaService, StatusPedidoService statusPedidoService, EnderecoService enderecoService) {
        this.pedidoService = pedidoService;
        this.usuarioService = usuarioService;
        this.empresaService = empresaService;
        this.statusPedidoService = statusPedidoService;
        this.enderecoService = enderecoService;
    }

    @PostMapping
    public ResponseEntity<Pedido> cadastrar(@RequestBody PedidoRequest pedidoRequest) {
        Optional<Usuario> usuario = usuarioService.findById(pedidoRequest.getUsuarioId());
        Optional<Empresa> empresa = empresaService.findById(pedidoRequest.getEmpresaId());
        Optional<StatusPedido> statusPedido = statusPedidoService.findById(pedidoRequest.getStatusId());
        Optional<Endereco> endereco = enderecoService.findById(pedidoRequest.getEnderecoId());
        Pedido pedido = pedidoService.aberturaPedido(usuario.get(), empresa.get(), statusPedido.get(), endereco.get());
        return ResponseEntity.ok(pedido);
    }
}
