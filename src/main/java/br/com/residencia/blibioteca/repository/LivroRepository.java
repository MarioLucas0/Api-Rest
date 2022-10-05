package br.com.residencia.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.blibioteca.entity.Livro;

public interface LivroRepository extends JpaRepository<Livro, Integer> {

}