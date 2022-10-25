package com.serratec.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerce.ecommerce.model.Categoria;
import com.serratec.ecommerce.ecommerce.model.Produto;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public Categoria findByProduto(Produto produto);
}