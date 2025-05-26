package com.example.cadastro_pessoas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "itens_compra")
@Data
public class ItemCompraModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "compra_id")
    private CompraModel compra;

    @Column(name = "produto_id")
    private Long produtoId;

    private Integer quantidade;

    @Column(name = "preco_unitario")
    private Double precoUnitario;
}
