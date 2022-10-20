package br.com.residencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Editora;

public interface EditoraRepository extends
	JpaRepository<Editora, Integer>
{

}
