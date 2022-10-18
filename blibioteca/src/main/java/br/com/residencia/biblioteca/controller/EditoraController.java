package br.com.residencia.biblioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {
	@Autowired
	EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<Editora>> getAllEditoras(){
		return new ResponseEntity<>(editoraService.getAllEditoras(),
				HttpStatus.OK);
	}

	@GetMapping("/dto")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasDTO(){
		return new ResponseEntity<>(editoraService.getAllEditorasDTO(),
				HttpStatus.OK);
	}

	@GetMapping("/editora-livros")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasLivrosDTO(){
		return new ResponseEntity<>(editoraService.getAllEditorasLivrosDTO(),
				HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		if(null != editora)
			return new ResponseEntity<>(editora,
					HttpStatus.OK);
		else
			return new ResponseEntity<>(editora,
					HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.saveEditora(editora),
				HttpStatus.CREATED);
	}

	@PostMapping("/dto")
	public ResponseEntity<EditoraDTO> saveEditoraDTO(@RequestBody EditoraDTO editoraDTO) {
		return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO),
				HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, 
			@PathVariable Integer id){
		return new ResponseEntity<>(editoraService.updateEditora(editora, id),
				HttpStatus.OK);
	}

	@PutMapping("/dto/{id}")
	public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, 
			@PathVariable Integer id){
		return new ResponseEntity<>(editoraService.updateEditoraDTO(editoraDTO, id),
				HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Editora> deleteEditora(@PathVariable Integer id) {
		Editora editora = editoraService.getEditoraById(id);
		if(null == editora)
			return new ResponseEntity<>(editora,
					HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(editoraService.deleteEditora(id),
					HttpStatus.OK);
	}

}
