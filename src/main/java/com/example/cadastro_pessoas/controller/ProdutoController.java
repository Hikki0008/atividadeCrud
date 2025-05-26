package com.example.cadastro_pessoas.controller;

import com.example.cadastro_pessoas.model.ProdutoModel;
import com.example.cadastro_pessoas.servicer.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // POST → Adicionar Produto
    @PostMapping
    public ResponseEntity<ProdutoModel> adicionar(@RequestBody ProdutoModel produto) {
        ProdutoModel novoProduto = produtoService.adicionarProduto(produto);
        return ResponseEntity.ok(novoProduto);
    }

    // GET → Listar Produtos
    @GetMapping
    public ResponseEntity<List<ProdutoModel>> listar() {
        return ResponseEntity.ok(produtoService.listarProdutos());
    }

    // GET → Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoModel> buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT → Atualizar Produto
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoModel> atualizar(@PathVariable Long id, @RequestBody ProdutoModel produto) {
        try {
            ProdutoModel atualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE → Remover Produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        produtoService.removerProduto(id);
        return ResponseEntity.noContent().build();
    }
}
