package com.example.cadastro_pessoas.controller;

import com.example.cadastro_pessoas.model.CompraModel;
import com.example.cadastro_pessoas.servicer.CompraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    // POST → Registrar uma nova compra
    @PostMapping
    public ResponseEntity<CompraModel> registrarCompra(@RequestBody CompraModel compra) {
        CompraModel novaCompra = compraService.registrarCompra(compra);
        return ResponseEntity.ok(novaCompra);
    }

    // GET → Listar todas as compras
    @GetMapping
    public ResponseEntity<List<CompraModel>> listarCompras() {
        return ResponseEntity.ok(compraService.listarCompras());
    }

    // GET → Buscar uma compra por ID
    @GetMapping("/{id}")
    public ResponseEntity<CompraModel> buscarPorId(@PathVariable Long id) {
        return compraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // GET → Listar compras de uma pessoa
    @GetMapping("/pessoa/{idPessoa}")
    public ResponseEntity<List<CompraModel>> buscarPorPessoa(@PathVariable Long idPessoa) {
        return ResponseEntity.ok(compraService.buscarPorPessoa(idPessoa));
    }

    // DELETE → Cancelar uma compra
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarCompra(@PathVariable Long id) {
        compraService.cancelarCompra(id);
        return ResponseEntity.noContent().build();
    }
}

