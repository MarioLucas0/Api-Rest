package br.com.residencia.cinema.controller;

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

import br.com.residencia.cinema.entity.Filme;
import br.com.residencia.cinema.services.FilmeService;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	FilmeService filmeService;
	
	@GetMapping
	public ResponseEntity<List<Filme>> getAllFilmes(){
		return new ResponseEntity<>(filmeService.getAllFilmes(),HttpStatus.OK);
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Filme> getFilmeById(@PathVariable Integer id){
		Filme filme = filmeService.getFilmeById(id);
		if(filme != null)
			return new ResponseEntity<>(filme,HttpStatus.OK);
		else
			return new ResponseEntity<>(filme,HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Filme> saveFilme(@RequestBody Filme filme){
		return new ResponseEntity<>(filmeService.saveFilme(filme),HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Filme> updateFilme(@RequestBody Filme filme,@PathVariable Integer id){
		return new ResponseEntity<>(filmeService.updateFilme(filme, id),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}") 
	public ResponseEntity<Filme> deleteFilme(@PathVariable Integer id){
		Filme filme = filmeService.getFilmeById(id);
		if(filme == null)
			return new ResponseEntity<>(filme,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(filmeService.deleteFilme(id),HttpStatus.OK);
		
	}
}
