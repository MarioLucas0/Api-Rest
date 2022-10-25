package com.serratec.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerce.ecommerce.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}