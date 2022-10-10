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

import br.com.residencia.cinema.entity.Diretor;
import br.com.residencia.cinema.services.DiretorService;

@RestController
@RequestMapping("/diretores")
public class DiretorController {
	
	@Autowired
	DiretorService diretorSerice;
	
	@GetMapping
	public ResponseEntity <List<Diretor>> getAllDiretores(){
		return new ResponseEntity<>(diretorSerice.getAllDiretores(),HttpStatus.OK);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Diretor> gettDiretorById(@PathVariable Integer id){
		Diretor diretor = diretorSerice.getDiretorById(id);
		if(diretor != null)
			return new ResponseEntity<>(diretorSerice.getDiretorById(id),HttpStatus.OK);
		else
			return new ResponseEntity<>(diretorSerice.getDiretorById(id),HttpStatus.NOT_FOUND);
	}
	
	@PostMapping
	public ResponseEntity<Diretor> saveDiretor(@RequestBody Diretor diretor){
		return new ResponseEntity<>(diretorSerice.saveDiretor(diretor),HttpStatus.CREATED);
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Diretor> updateDiretor(@RequestBody Diretor diretor ,@PathVariable Integer id){
		return new ResponseEntity<>(diretorSerice.updateDiretor(diretor, id),HttpStatus.OK);
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Diretor> deleteDiretor(@PathVariable Integer id){
		Diretor diretor = diretorSerice.getDiretorById(id);
		if(diretor == null)
			return new ResponseEntity<>(diretor,HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(diretorSerice.deleteDiretor(id),HttpStatus.OK);
		
	}
}
