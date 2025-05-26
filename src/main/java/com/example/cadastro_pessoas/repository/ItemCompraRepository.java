package com.example.cadastro_pessoas.repository;

import com.example.cadastro_pessoas.model.ItemCompraModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompraModel, Long> {
}
