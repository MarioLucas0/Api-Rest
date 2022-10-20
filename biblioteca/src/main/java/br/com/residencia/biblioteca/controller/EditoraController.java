package br.com.residencia.biblioteca.controller;

import java.util.List;

import javax.validation.Valid;

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

import br.com.residencia.biblioteca.dto.ConsultaCnpjDTO;
import br.com.residencia.biblioteca.dto.EditoraDTO;
import br.com.residencia.biblioteca.entity.Editora;
import br.com.residencia.biblioteca.service.EditoraService;

@RestController
@RequestMapping("/editoras")
public class EditoraController {
//	C = Create
//	R = Read
//	U = Update
//	D = Delete
	
	@Autowired
	EditoraService editoraService;

//	Search
	@GetMapping("/search")
	public ResponseEntity<List<Editora>> getAllEditoras() {
		return new ResponseEntity<>(editoraService.getAllEditora(), HttpStatus.OK);
	}
	
	@GetMapping("/search/dto")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasDTO() {
		return new ResponseEntity<>(editoraService.getAllEditoraDTO(), HttpStatus.OK);
	}
	
	@GetMapping("/search/editora-dto")
	public ResponseEntity<List<EditoraDTO>> getAllEditorasLivrosDTO() {
		return new ResponseEntity<>(editoraService.getAllEditorasLivrosDTO(), HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Editora> getEditoraById(@PathVariable Integer id) {
		return new ResponseEntity<>(editoraService.getEditoraById(id), HttpStatus.OK);
	}
	
	@GetMapping("/search/dto/{id}")
	public ResponseEntity<EditoraDTO> getEditoraDtoById(@PathVariable Integer id) {
		EditoraDTO editora = editoraService.getEditoraDtoById(id);
		if(editora == null) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(editora, HttpStatus.OK);
		}
	}
	
	@GetMapping("/search/api/{cnpj}")
	public ResponseEntity<ConsultaCnpjDTO> getCnpj(@PathVariable String cnpj) {
		ConsultaCnpjDTO consultaCnpjDTO = editoraService.consultaCnpjApiExterna(cnpj);
		if(consultaCnpjDTO != null) {
			return new ResponseEntity<>(consultaCnpjDTO, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(consultaCnpjDTO, HttpStatus.NOT_FOUND);
		}
	}
	
//	Save
	@PostMapping("/save")
	public ResponseEntity<Editora> saveEditora(@RequestBody Editora editora) {
		return new ResponseEntity<>(editoraService.saveEditora(editora), HttpStatus.CREATED);
	}
	
	@GetMapping("/save/external/{cnpj}")
	public ResponseEntity<Editora> saveEditoraFromExternalSource(@PathVariable String cnpj) {
		return new ResponseEntity<>(editoraService.saveFromExternalSource(cnpj), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Editora>> saveAllEditoras(@RequestBody List<Editora> editora) {
		return new ResponseEntity<>(editoraService.saveAllEditoras(editora), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll/dto")
	public ResponseEntity<List<EditoraDTO>> saveAllEditorasDTO(@RequestBody List<EditoraDTO> editora) {
		return new ResponseEntity<>(editoraService.saveAllEditorasDTO(editora), HttpStatus.CREATED);
	}
	
	@PostMapping("/save/dto")
	public ResponseEntity<EditoraDTO> saveEditoraDTO(@Valid @RequestBody EditoraDTO editoraDTO) {
		return new ResponseEntity<>(editoraService.saveEditoraDTO(editoraDTO), HttpStatus.CREATED);
	}

//	Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Editora> deleteEditora(@PathVariable Integer id) {
		return new ResponseEntity<>(editoraService.deleteEditora(id), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/dto/{id}")
	public ResponseEntity<EditoraDTO> deleteEditoraDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(editoraService.deleteEditoraDTO(id), HttpStatus.OK);
	}

//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Editora> updateEditora(@RequestBody Editora editora, @PathVariable Integer id) {
		return new ResponseEntity<>(editoraService.updateEditora(editora, id), HttpStatus.OK);
	}
	
	@PutMapping("/update/dto/{id}")
	public ResponseEntity<EditoraDTO> updateEditoraDTO(@RequestBody EditoraDTO editoraDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(editoraService.updateEditoraDTO(editoraDTO, id), HttpStatus.OK);
	}
	
}
