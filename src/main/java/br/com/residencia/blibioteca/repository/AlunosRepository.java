package br.com.residencia.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.blibioteca.entity.Alunos;

public interface AlunosRepository extends JpaRepository<Alunos, Integer> {

}