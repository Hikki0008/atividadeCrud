package com.example.cadastro_pessoas.servicer;

import com.example.cadastro_pessoas.model.CompraModel;
import com.example.cadastro_pessoas.model.ItemCompraModel;
import com.example.cadastro_pessoas.model.ProdutoModel;
import com.example.cadastro_pessoas.repository.CompraRepository;
import com.example.cadastro_pessoas.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public CompraModel registrarCompra(CompraModel compra) {
        // Atualizar o estoque dos produtos
        for (ItemCompraModel item : compra.getItens()) {
            ProdutoModel produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualiza o estoque
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtoRepository.save(produto);

            // Define a relação do item com a compra
            item.setCompra(compra);
            item.setPrecoUnitario(produto.getPreco());
        }

        return compraRepository.save(compra);
    }

    public List<CompraModel> listarCompras() {
        return compraRepository.findAll();
    }

    public Optional<CompraModel> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public List<CompraModel> buscarPorPessoa(Long pessoaId) {
        return compraRepository.findByPessoaId(pessoaId);
    }

    public void cancelarCompra(Long id) {
        CompraModel compra = compraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compra não encontrada"));

        // Repor os produtos no estoque
        for (ItemCompraModel item : compra.getItens()) {
            ProdutoModel produto = produtoRepository.findById(item.getProdutoId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        compraRepository.deleteById(id);
    }
}
