package com.example.cadastro_pessoas.repository;

import com.example.cadastro_pessoas.model.CompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<CompraModel, Long> {
    List<CompraModel> findByPessoaId(Long pessoaId);
}
