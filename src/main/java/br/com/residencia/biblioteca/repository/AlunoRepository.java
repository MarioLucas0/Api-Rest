package br.com.residencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Aluno;

public interface AlunoRepository extends
	JpaRepository<Aluno, Integer>
{

}
