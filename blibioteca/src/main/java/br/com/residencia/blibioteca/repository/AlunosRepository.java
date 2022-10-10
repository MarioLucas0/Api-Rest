package br.com.residencia.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.blibioteca.entity.Aluno;

public interface AlunosRepository extends JpaRepository<Aluno, Integer> {

}