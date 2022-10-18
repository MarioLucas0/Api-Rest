package br.com.residencia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivroDTO;
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

	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		Livro livro = toEntidade(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
		
		LivroDTO livroAtualizadoDTO = toDTO(novoLivro);
		return livroAtualizadoDTO;
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
	
	public LivroDTO updateLivroDTO(LivroDTO livroDTO, Integer id) {
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
		
		if(livroExistenteNoBanco != null) {
			
			livroExistenteNoBanco = toEntidade(livroDTO);
			
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
			
			livroAtualizadoDTO = toDTO(livroAtualizado);
			
		}
		return livroAtualizadoDTO;
	}
	
	public Livro toEntidade (LivroDTO livroDTO) {
		Livro livro = new Livro();
		
		livro.setCodigoIsbn(livroDTO.getCodigoIsbn());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		livro.setNomeLivro(livroDTO.getNomeLivro());
		
		return livro;
	}
	
	public LivroDTO toDTO(Livro livro) {
		LivroDTO livroDTO = new LivroDTO();
		
		livroDTO.setCodigoIsbn(livro.getCodigoIsbn());
		livroDTO.setCodigoLivro(livro.getCodigoLivro());
		livroDTO.setDataLancamento(livro.getDataLancamento());
		livroDTO.setNomeAutor(livro.getNomeAutor());
		livroDTO.setNomeLivro(livro.getNomeLivro());
		
		return livroDTO;
	}

	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
}