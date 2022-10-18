package com.blibiotecaex.blibioteca.repository;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibiotecaex.blibioteca.dto.AutorDTO;
import com.blibiotecaex.blibioteca.entities.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

  Autor save(@Valid AutorDTO li);

}
