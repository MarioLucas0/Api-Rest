package br.com.residencia.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.blibioteca.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

}