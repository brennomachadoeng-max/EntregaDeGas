package com.brenno.entrega.controller;

import com.brenno.entrega.DTO.PedidoProdutoRequest;
import com.brenno.entrega.DTO.pedido.PedidoRequest;
import com.brenno.entrega.DTO.pedido.PedidoResponseDTO;
import com.brenno.entrega.model.*;
import com.brenno.entrega.service.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    private final PedidoService pedidoService;
    private final PedidoProdutoService pedidoProdutoService;

    public PedidoController(PedidoService pedidoService,
                            PedidoProdutoService pedidoProdutoService) {
        this.pedidoService = pedidoService;
        this.pedidoProdutoService = pedidoProdutoService;
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> cadastrar(@RequestBody PedidoRequest pedidoRequest) {
        Pedido pedido = pedidoService.criarPedido(pedidoRequest);
        pedidoProdutoService.adicionandoProdutoAoPedido(pedido, pedidoRequest.getProdutos());
        PedidoResponseDTO response = new PedidoResponseDTO(pedido.getIdPedido(), "Pedido criado com sucesso");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
