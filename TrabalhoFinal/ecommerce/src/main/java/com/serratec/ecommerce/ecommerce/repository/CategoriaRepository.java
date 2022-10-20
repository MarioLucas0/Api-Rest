package com.serratec.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerce.ecommerce.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}