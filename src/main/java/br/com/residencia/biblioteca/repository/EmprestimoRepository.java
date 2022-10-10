package br.com.residencia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Emprestimo;

public interface EmprestimoRepository
	extends JpaRepository<Emprestimo,Integer>
{

}
