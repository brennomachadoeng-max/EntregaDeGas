package com.brenno.entrega.Controller;

import com.brenno.entrega.model.Entregador;
import com.brenno.entrega.service.EntregadorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entregadores")
public class EntregadorController {

    private final EntregadorService entregadorService;

    public EntregadorController(EntregadorService entregadorService) {
        this.entregadorService = entregadorService;
    }

    @PostMapping
    public ResponseEntity<Entregador> save(@RequestBody Entregador entregador) {
        Entregador salvo = entregadorService.save(entregador);
        return ResponseEntity.ok(salvo);
    }

    @PostMapping("/{id}/localizacao")
    public ResponseEntity<Entregador> atualizarLocalizacao(@PathVariable Integer id, @RequestBody Entregador entregador) {
        Entregador atualizado = entregadorService.atualizarLocalizcao(id, entregador);
        return ResponseEntity.ok(atualizado);
    }


}
