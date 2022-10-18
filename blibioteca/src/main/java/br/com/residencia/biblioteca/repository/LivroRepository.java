package br.com.residencia.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;

public interface LivroRepository
	extends JpaRepository<Livro,Integer>
{
	public List<Livro> findByEditora(Editora editora);
	
	public List<Livro> findByNomeLivro(String nome);
	
	public List<Livro> findByNomeAutor(String autor);
}
