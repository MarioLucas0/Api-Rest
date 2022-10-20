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
import br.com.residencia.biblioteca.dto.EmprestimoDTO;
import br.com.residencia.biblioteca.entity.Emprestimo;
import br.com.residencia.biblioteca.service.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
//	C = Create
//	R = Read
//	U = Update
//	D = Delete
	
	@Autowired
	EmprestimoService emprestimoService;

//	Search
	@GetMapping("/search")
	public ResponseEntity<List<Emprestimo>> getAllEmprestimos() {
		return new ResponseEntity<>(emprestimoService.getAllEmprestimos(), HttpStatus.OK);
	}
	
	@GetMapping("/search/dto")
	public ResponseEntity<List<EmprestimoDTO>> getAllEmprestimosDTO() {
		return new ResponseEntity<>(emprestimoService.getAllEmprestimosDTO(), HttpStatus.OK);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<Emprestimo> getEmprestimoById(@PathVariable Integer id) {
		return new ResponseEntity<>(emprestimoService.getEmprestimoById(id), HttpStatus.OK);
	}

	@GetMapping("/search/dto/{id}")
	public ResponseEntity<EmprestimoDTO> getEmprestimoDtoById(@PathVariable Integer id) {
		EmprestimoDTO emprestimo = emprestimoService.getEmprestimoDtoById(id);
		if(emprestimo == null) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(emprestimo, HttpStatus.OK);
		}
	}
	
//	Save
	@PostMapping("/save")
	public ResponseEntity<Emprestimo> saveEmprestimo(@RequestBody Emprestimo emprestimo) {
		return new ResponseEntity<>(emprestimoService.saveEmprestimo(emprestimo), HttpStatus.OK);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Emprestimo>> saveAllEmprestimos(@RequestBody List<Emprestimo> emprestimo) {
		return new ResponseEntity<>(emprestimoService.saveAllEmprestimos(emprestimo), HttpStatus.OK);
	}
	
	@PostMapping("/save/dto")
	public ResponseEntity<EmprestimoDTO> saveEmprestimoDTO(@RequestBody EmprestimoDTO emprestimoDTO) {
		return new ResponseEntity<>(emprestimoService.saveEmprestimoDTO(emprestimoDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll/dto")
	public ResponseEntity<List<EmprestimoDTO>> saveAllEmprestimosDTO(@RequestBody List<EmprestimoDTO> emprestimo) {
		return new ResponseEntity<>(emprestimoService.saveAllEmprestimosDTO(emprestimo), HttpStatus.OK);
	}

//	Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Emprestimo> deleteEmprestimo(@PathVariable Integer id) {
		return new ResponseEntity<>(emprestimoService.deleteEmprestimo(id), HttpStatus.OK);
	}

	@DeleteMapping("/delete/dto/{id}")
	public ResponseEntity<EmprestimoDTO> deleteEmprestimoDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(emprestimoService.deleteEmprestimoDTO(id), HttpStatus.OK);
	}
	
//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Emprestimo> updateEmprestimo(@RequestBody Emprestimo emprestimo, @PathVariable Integer id) {
		return new ResponseEntity<>(emprestimoService.updateEmprestimo(emprestimo, id), HttpStatus.OK);
	}
	
	@PutMapping("/update/dto/{id}")
	public ResponseEntity<EmprestimoDTO> updateEmprestimoDTO(@RequestBody EmprestimoDTO emprestimoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(emprestimoService.updateEmprestimoDTO(emprestimoDTO, id), HttpStatus.OK);
	}
}
