package com.example.cadastro_pessoas.servicer;

import com.example.cadastro_pessoas.model.ProdutoModel;
import com.example.cadastro_pessoas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Adicionar Produto
    public ProdutoModel adicionarProduto(ProdutoModel produto) {
        return produtoRepository.save(produto);
    }

    // Listar todos os produtos
    public List<ProdutoModel> listarProdutos() {
        return produtoRepository.findAll();
    }

    // Buscar por ID
    public Optional<ProdutoModel> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    // Atualizar Produto
    public ProdutoModel atualizarProduto(Long id, ProdutoModel produtoAtualizado) {
        ProdutoModel produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        produto.setDescricao(produtoAtualizado.getDescricao());

        return produtoRepository.save(produto);
    }

    // Remover Produto
    public void removerProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}
