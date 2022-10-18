package com.blibiotecaex.blibioteca.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibiotecaex.blibioteca.dto.LivroDTO;
import com.blibiotecaex.blibioteca.entities.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

  Livro save(@Valid LivroDTO li);

}
