package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository;
	
	public List<Livro> getAllLivros(){
		return livroRepository.findAll();
	}
	
	public Livro getLivroById(Integer id) {
		return livroRepository.findById(id).get();
		//return livroRepository.findById(id).orElse(null);
	}
	
	public Livro saveLivro(Livro livro) {
		return livroRepository.save(livro);
	}
	
	public Livro updateLivro(Livro livro, Integer id) {
		//Livro livroExistenteNoBanco = livroRepository.findById(id).get();
		
		Livro livroExistenteNoBanco = getLivroById(id);

		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		//livroExistenteNoBanco.setEditora(Editora);
		//livroExistenteNoBanco.setEmprestimo(Emprestimo);
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		
		return livroRepository.save(livroExistenteNoBanco);
		
		//return livroRepository.save(livro);
	}

	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
}