// Pacote onde está localizada a interface de repositório
package com.example.cadastro_pessoas.repository;

// Importações necessárias para o Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository; // Interface que fornece métodos prontos para CRUD
import org.springframework.stereotype.Repository; // Anotação para identificar como um repositório

import com.example.cadastro_pessoas.model.PessoasModel;

/**
 * Anotação para indicar que esta interface é um repositório Spring.
 * Um repositório é responsável pela interação com o banco de dados.
 */
@Repository
public interface PessoasRepository extends JpaRepository<PessoasModel, Long> {
}
