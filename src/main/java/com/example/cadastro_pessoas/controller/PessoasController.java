// Pacote onde está localizado o Controller
package com.example.cadastro_pessoas.controller;

// Importações necessárias
import java.util.List; // Para lidar com listas de objetos

import org.springframework.beans.factory.annotation.Autowired; // Injeção de dependência
import org.springframework.http.ResponseEntity; // Representa respostas HTTP
import org.springframework.web.bind.annotation.DeleteMapping; // Mapeia requisições DELETE
import org.springframework.web.bind.annotation.GetMapping; // Mapeia requisições GET
import org.springframework.web.bind.annotation.PathVariable; // Captura parâmetros da URL
import org.springframework.web.bind.annotation.PostMapping; // Mapeia requisições POST
import org.springframework.web.bind.annotation.PutMapping; // Mapeia requisições PUT
import org.springframework.web.bind.annotation.RequestBody; // Indica que o corpo da requisição é o objeto
import org.springframework.web.bind.annotation.RequestMapping; // Mapeia o caminho base da API
import org.springframework.web.bind.annotation.RestController; // Indica que esta classe é um Controller REST

import com.example.cadastro_pessoas.model.PessoasModel;
import com.example.cadastro_pessoas.servicer.PessoasService;

// Anotação que define esta classe como um Controller REST
@RestController

// Caminho base para todas as requisições deste controller
@RequestMapping("/api/pessoas")
public class PessoasController {

    // Injeção de dependência do serviço que vai realizar a lógica de negócio
    @Autowired
    private PessoasService service;

    /**
     * Método GET para listar todas as pessoas cadastradas.
     * URL: /api/pessoas
     * Método HTTP: GET
     * Retorno: Lista de PessoasModel
     */
    @GetMapping
    public List<PessoasModel> listarTodos() {
        // Chama o método do service que retorna a lista de todas as pessoas
        return service.listarTodos();
    }

    /**
     * Método GET para buscar uma pessoa específica pelo ID.
     * URL: /api/pessoas/{id}
     * Método HTTP: GET
     * Parâmetro: ID da pessoa a ser buscada (capturado da URL)
     * Retorno: ResponseEntity com a Pessoa encontrada ou 404 (Not Found)
     */
    @GetMapping("/{id}")
    public ResponseEntity<PessoasModel> buscarPorId(@PathVariable Long id) {
        // Busca a pessoa pelo ID, se encontrar, retorna 200 (OK), se não, retorna 404 (Not Found)
        return service.buscarPorId(id)
                     .map(ResponseEntity::ok) // Converte o resultado em ResponseEntity com status 200
                     .orElse(ResponseEntity.notFound().build()); // Retorna 404 se não encontrar
    }

    /**
     * Método POST para salvar uma nova pessoa.
     * URL: /api/pessoas
     * Método HTTP: POST
     * Parâmetro: Objeto do tipo PessoasModel no corpo da requisição (JSON)
     * Retorno: Objeto salvo
     */
    @PostMapping
    public PessoasModel salvar(@RequestBody PessoasModel pessoasModel) {
        // Chama o método de salvar do service e retorna o objeto persistido
        return service.salvar(pessoasModel);
    }

    /**
     * Método PUT para atualizar os dados de uma pessoa existente.
     * URL: /api/pessoas/{id}
     * Método HTTP: PUT
     * Parâmetros: ID da pessoa (na URL) e o objeto com os novos dados (no corpo da requisição)
     * Retorno: ResponseEntity com a pessoa atualizada ou 404 (Not Found) se não encontrar
     */
    @PutMapping("/{id}")
    public ResponseEntity<PessoasModel> atualizar(@PathVariable Long id, @RequestBody PessoasModel pessoasModel) {
        // Verifica se o ID existe no banco de dados
        if (!service.buscarPorId(id).isPresent()) {
            // Se não encontrar, retorna 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
        // Define o ID no objeto (caso não tenha sido passado no corpo)
        pessoasModel.setId(id);
        // Salva o objeto atualizado e retorna 200 (OK)
        return ResponseEntity.ok(service.salvar(pessoasModel));
    }

    /**
     * Método DELETE para excluir uma pessoa do banco de dados.
     * URL: /api/pessoas/{id}
     * Método HTTP: DELETE
     * Parâmetro: ID da pessoa a ser excluída
     * Retorno: ResponseEntity com status 204 (No Content) ou 404 (Not Found) se não encontrar
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        // Verifica se o ID existe no banco de dados
        if (!service.buscarPorId(id).isPresent()) {
            // Se não encontrar, retorna 404 (Not Found)
            return ResponseEntity.notFound().build();
        }
        // Se encontrar, chama o método para deletar
        service.deletar(id);
        // Retorna 204 (No Content), que significa que foi deletado com sucesso, mas sem conteúdo
        return ResponseEntity.noContent().build();
    }
}
