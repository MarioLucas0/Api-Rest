package br.com.residencia.biblioteca.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.repository.EditoraRepository;
import br.com.residencia.biblioteca.repository.LivroRepository;

@Service
public class EditoraService {
	@Autowired
	EditoraRepository editoraRepository;

	@Autowired
	LivroRepository livroRepository;

	@Autowired
	LivroService livroService;
	
	
	public List<Editora> getAllEditoras(){
		return editoraRepository.findAll();
	}
	
	public List<EditoraDTO> getAllEditorasDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		//1. Percorrer a lista de entidades Editora (chamada listaEditora)
		//2. Na lista de entidade, pegar cada entidade existente nela
		for(Editora editora: listaEditora) {
			//3. Transformar cada entidade existente na lista em um DTO
			EditoraDTO editoraDTO = toDTO(editora);

			//OBS: para converter a entidade no DTO, usar o metodo toDTO
			//4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de DTO
			listaEditoraDTO.add(editoraDTO);
		}
		
		//5. Retornar/devolver a lista de DTO preenchida
		return listaEditoraDTO; 
	}
	
	public Editora getEditoraById(Integer id) {
		return editoraRepository.findById(id).orElse(null);
	}
	
	public Editora saveEditora(Editora editora) {
		return editoraRepository.save(editora);
	}

	public EditoraDTO saveEditoraDTO(EditoraDTO editoraDTO) {
		Editora editora = toEntidade(editoraDTO);
		Editora novaEditora = editoraRepository.save(editora);
		
		EditoraDTO editoraAtualizadaDTO = toDTO(novaEditora);
		return editoraAtualizadaDTO;
	}
/*
	public EditoraDTO saveEditoraDTOOtimizado(EditoraDTO editoraDTO) {
		Editora novaEditora = editoraRepository.save(toEntidade(editoraDTO));
		return toDTO(novaEditora);
	}

	public EditoraDTO saveEditoraDTOOtimizadoTwo(EditoraDTO editoraDTO) {
		return toDTO(editoraRepository.save(toEntidade(editoraDTO)));
	}
*/	
	
	public EditoraDTO updateEditoraDTO(EditoraDTO editoraDTO, Integer id) {
		Editora editoraExistenteNoBanco = getEditoraById(id);
		EditoraDTO editoraAtualizadaDTO = new EditoraDTO();
		
		if(editoraExistenteNoBanco != null) {
			
			editoraExistenteNoBanco = toEntidade(editoraDTO);
			
			Editora editoraAtualizada = editoraRepository.save(editoraExistenteNoBanco);
			
			editoraAtualizadaDTO = toDTO(editoraAtualizada);
			
		}
		return editoraAtualizadaDTO;
	}
	
	private Editora toEntidade (EditoraDTO editoraDTO) {
		Editora editora = new Editora();
		
		editora.setNome(editoraDTO.getNome());
		return editora;
	}
	
	private EditoraDTO toDTO(Editora editora) {
		EditoraDTO editoraDTO = new EditoraDTO();
		
		editoraDTO.setCodigoEditora(editora.getCodigoEditora());
		editoraDTO.setNome(editora.getNome());
		
		return editoraDTO;
	}
	
	public Editora updateEditora(Editora editora, Integer id) {
		//Editora editoraExistenteNoBanco = editoraRepository.findById(id).get();
		
		Editora editoraExistenteNoBanco = getEditoraById(id);

		editoraExistenteNoBanco.setNome(editora.getNome());
		
		return editoraRepository.save(editoraExistenteNoBanco);
		
		//return editoraRepository.save(editora);
	}

	public Editora deleteEditora(Integer id) {
		editoraRepository.deleteById(id);
		return getEditoraById(id);
	}
	
	public List<EditoraDTO> getAllEditorasLivrosDTO(){
		List<Editora> listaEditora = editoraRepository.findAll();
		List<EditoraDTO> listaEditoraDTO = new ArrayList<>();
		
		
		for(Editora editora: listaEditora) {
			EditoraDTO editoraDTO = toDTO(editora);
			List<Livro> listaLivros = new ArrayList<>();
			List<LivroDTO> listaLivrosDTO = new ArrayList<>();
			
			
			listaLivros = livroRepository.findByEditora(editora);
			for(Livro livro : listaLivros) {
				LivroDTO livroDTO = livroService.toDTO(livro);
				listaLivrosDTO.add(livroDTO);
			}
			editoraDTO.setListaLivrosDTO(listaLivrosDTO);

			listaEditoraDTO.add(editoraDTO);
		}
		
		return listaEditoraDTO; 
	}

}