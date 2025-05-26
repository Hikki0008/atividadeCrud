package com.example.cadastro_pessoas.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "compras")
@Data
public class CompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataCompra = LocalDateTime.now();

    @Column(name = "pessoa_id")
    private Long pessoaId;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL)
    private List<ItemCompraModel> itens;
}
