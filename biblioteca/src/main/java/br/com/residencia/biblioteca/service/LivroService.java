package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class LivroService {
	@Autowired
	LivroRepository livroRepository; 

	@Autowired
	private ModelMapper modelMapper;

//	Get
	public List<Livro> getAllLivros() {
		return livroRepository.findAll();	
	}
	
	public List<LivroDTO> getAllLivrosDTO() {
		List<Livro> livro = getAllLivros();
		List<LivroDTO> livroDTO = new ArrayList<LivroDTO>();
		livro.forEach(lvr -> {
			livroDTO.add(converteEntitytoDTO(lvr));
		});
		return livroDTO;
	}
	
	public Livro getLivroById(Integer id) {
		return livroRepository.findById(id).orElse(null);
	}
	
	public LivroDTO getLivroDtoById(Integer id) {
		Livro livro = livroRepository.findById(id).orElse(null);
		if(livro != null) {
			return converteEntitytoDTO(livro);
		}
		return null;
	}
	
//	Save
	public Livro saveLivro(Livro livro) {
		livro = formatToUpper(livro);
		return livroRepository.save(livro);
	}
	
	public List<Livro> saveAllLivros(List<Livro> livro) {
		livro.forEach(lvr -> {
			formatToUpper(lvr);
		});
		return livroRepository.saveAll(livro);
	}
	
	public LivroDTO saveLivroDTO(LivroDTO livroDTO) {
		livroDTO = formatToUpperDTO(livroDTO);
		Livro livro = toEntity(livroDTO);
		Livro novoLivro = livroRepository.save(livro);
		
		LivroDTO livroAtualizadoDTO = converteEntitytoDTO(novoLivro);
		return livroAtualizadoDTO;
	}
	
	public List<LivroDTO> saveAllLivrosDTO(List<LivroDTO> livroDTO) {
		livroDTO.forEach(lvr -> {
			formatToUpperDTO(lvr);
			livroRepository.save(toEntity(lvr));
		});
		return livroDTO;
	}
	
//	Update
	public Livro updateLivro(Livro livro, Integer id) {
		livro = formatToUpper(livro);
		Livro livroExistenteNoBanco = getLivroById(id);
		
		livroExistenteNoBanco.setCodigoIsbn(livro.getCodigoIsbn());
		livroExistenteNoBanco.setDataLancamento(livro.getDataLancamento());
		livroExistenteNoBanco.setEditora(livro.getEditora());
		livroExistenteNoBanco.setNomeAutor(livro.getNomeAutor());
		livroExistenteNoBanco.setNomeLivro(livro.getNomeLivro());
		
		return livroRepository.save(livroExistenteNoBanco);
	}
	
	public LivroDTO updateLivroDTO(LivroDTO livroDTO, Integer id) {
		livroDTO = formatToUpperDTO(livroDTO);
		Livro livroExistenteNoBanco = getLivroById(id);
		LivroDTO livroAtualizadoDTO = new LivroDTO();
		if(livroExistenteNoBanco != null) {
			livroExistenteNoBanco = toEntity(livroDTO);
			Livro livroAtualizado = livroRepository.save(livroExistenteNoBanco);
			livroAtualizadoDTO = converteEntitytoDTO(livroAtualizado);
		}
		return livroAtualizadoDTO;
	}
	
//	Delete
	public Livro deleteLivro(Integer id) {
		livroRepository.deleteById(id);
		return getLivroById(id);
	}
	
	public LivroDTO deleteLivroDTO(Integer id) {
		livroRepository.deleteById(id);
		return getLivroDtoById(id);
	}
	
//	Convert
	public LivroDTO converteEntitytoDTO(Livro livro) {
		LivroDTO editoraDTO = new LivroDTO();
		editoraDTO = (modelMapper.map(livro, LivroDTO.class));
		return editoraDTO;	
	}
	
	public Livro toEntity(LivroDTO livroDTO) {
		Livro livro = new Livro();
		livro.setCodigoIsbn(livroDTO.getCodigoIsbn());
		livro.setDataLancamento(livroDTO.getDataLancamento());
		livro.setNomeAutor(livroDTO.getNomeAutor());
		livro.setNomeLivro(livroDTO.getNomeLivro());
		return livro;	
	}
	
//	Format
	private Livro formatToUpper(Livro livro) {
		livro.setNomeAutor(livro.getNomeAutor().toUpperCase());
		livro.setNomeLivro(livro.getNomeLivro().toUpperCase());
		return livro;
	}
	
	private LivroDTO formatToUpperDTO(LivroDTO livroDTO) {
		livroDTO.setNomeAutor(livroDTO.getNomeAutor().toUpperCase());
		livroDTO.setNomeLivro(livroDTO.getNomeLivro().toUpperCase());
		return livroDTO;
	}
}
