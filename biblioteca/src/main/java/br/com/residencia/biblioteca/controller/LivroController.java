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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import br.com.residencia.biblioteca.dto.LivroDTO;
import br.com.residencia.biblioteca.entity.Livro;
import br.com.residencia.biblioteca.service.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {
//	C = Create
//	R = Read
//	U = Update
//	D = Delete
	
	@Autowired
	LivroService livroService;

//	Search
	@GetMapping("/search")
	public ResponseEntity<List<Livro>> getAllLivros() {
		return new ResponseEntity<>(livroService.getAllLivros(), HttpStatus.OK);
	}

	@GetMapping("/search/dto")
	public ResponseEntity<List<LivroDTO>> getAllLivrosDTO() {
		return new ResponseEntity<>(livroService.getAllLivrosDTO(), HttpStatus.OK);
	}
	
	@GetMapping("/search/{id}")
	public ResponseEntity<Livro> getLivroById(@PathVariable Integer id) {
		return new ResponseEntity<>(livroService.getLivroById(id), HttpStatus.OK);
	}

	@GetMapping("/search/dto/{id}")
	public ResponseEntity<LivroDTO> getLivroDtoById(@PathVariable Integer id) {
		LivroDTO livro = livroService.getLivroDtoById(id);
		if(livro == null) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(livro, HttpStatus.OK);
		}
	}
	
//	Save
	@PostMapping("/save")
	public ResponseEntity<Livro> saveLivro(@RequestBody Livro livro) {
		return new ResponseEntity<>(livroService.saveLivro(livro), HttpStatus.OK);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Livro>> saveAllLivros(@RequestBody List<Livro> livro) {
		return new ResponseEntity<>(livroService.saveAllLivros(livro), HttpStatus.OK);
	}
	
	@PostMapping("/save/dto")
	public ResponseEntity<LivroDTO> saveLivroDTO(@RequestBody LivroDTO livroDTO) {
		return new ResponseEntity<>(livroService.saveLivroDTO(livroDTO), HttpStatus.OK);
	}
	
	@PostMapping("/saveAll/dto")
	public ResponseEntity<List<LivroDTO>> saveAllLivrosDTO(@RequestBody List<LivroDTO> livroDTO) {
		return new ResponseEntity<>(livroService.saveAllLivrosDTO(livroDTO), HttpStatus.OK);
	}
	
//	Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Livro> deleteLivro(@PathVariable Integer id) {
		return new ResponseEntity<>(livroService.deleteLivro(id), HttpStatus.OK);
	}

	@DeleteMapping("/delete/dto/{id}")
	public ResponseEntity<LivroDTO> deleteLivroDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(livroService.deleteLivroDTO(id), HttpStatus.OK);
	}
	
//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Livro> updateLivro(@RequestBody Livro livro, @PathVariable Integer id) {
		return new ResponseEntity<>(livroService.updateLivro(livro, id), HttpStatus.OK);
	}
	
	@PutMapping("/update/dto/{id}")
	public ResponseEntity<LivroDTO> updateLivroDTO(@RequestBody LivroDTO livroDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(livroService.updateLivroDTO(livroDTO, id), HttpStatus.OK);
	}
}
