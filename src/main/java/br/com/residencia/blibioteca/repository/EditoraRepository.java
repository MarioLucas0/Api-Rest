package br.com.residencia.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.blibioteca.entity.Editora;

public interface EditoraRepository extends JpaRepository<Editora, Integer> {

}