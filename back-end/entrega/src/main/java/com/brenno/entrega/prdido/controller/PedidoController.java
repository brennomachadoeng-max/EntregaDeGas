package com.brenno.entrega.prdido.controller;

import com.brenno.entrega.prdido.dto.PedidoRequest;
import com.brenno.entrega.prdido.dto.PedidoResponseDTO;
import com.brenno.entrega.prdido.itemPedido.service.ItemPedidoService;
import com.brenno.entrega.prdido.model.Pedido;
import com.brenno.entrega.prdido.service.PedidoService;
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
    private final ItemPedidoService pedidoProdutoService;

    public PedidoController(PedidoService pedidoService,
                            ItemPedidoService pedidoProdutoService) {
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
