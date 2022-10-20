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

import br.com.residencia.biblioteca.dto.AlunoDTO;
import br.com.residencia.biblioteca.dto.AlunoEmprestimoDTO;
import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {
//	C = Create
//	R = Read
//	U = Update
//	D = Delete
	
	@Autowired
	AlunoService alunoService;

//	Search
	@GetMapping("/search")
	public ResponseEntity<List<Aluno>> getAllAlunos() {
		return new ResponseEntity<>(alunoService.getAllAlunos(), HttpStatus.OK);
	}

	@GetMapping("/search/{id}")
	public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
		Aluno aluno = alunoService.getAlunoById(id);
		if(aluno != null) {
			return new ResponseEntity<>(aluno, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(aluno, HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/search/dto")
	public ResponseEntity<List<AlunoDTO>> getAllAlunosDTO() {
		return new ResponseEntity<>(alunoService.getAllAlunosDTO(), HttpStatus.OK);
	}

	@GetMapping("/search/dto/{id}")
	public ResponseEntity<AlunoDTO> getEditoraDtoById(@PathVariable Integer id) {
		AlunoDTO aluno = alunoService.getAlunoDtoById(id);
		if(aluno == null) {			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(aluno, HttpStatus.OK);
		}
	}
	
	@GetMapping("/search/aluno-emprestimo/dto")
	public ResponseEntity<List<AlunoDTO>> getAllAlunosEmprestimosDTO() {
		return new ResponseEntity<>(alunoService.getAllEmprestimosAlunosDTO(), HttpStatus.OK);
	}
	
	@GetMapping("/search/filter/aluno-emprestimo/dto")
	public ResponseEntity<List<AlunoEmprestimoDTO>> filterAllAlunosEmprestimosDTO() {
		return new ResponseEntity<>(alunoService.filterAlunoEmprestimo(), HttpStatus.OK);
	}
//	Save
	@PostMapping("/save")
	public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno) {
		return new ResponseEntity<>(alunoService.saveAluno(aluno), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll")
	public ResponseEntity<List<Aluno>> saveAllAlunos(@RequestBody List<Aluno> aluno) {
		return new ResponseEntity<>(alunoService.saveAllAluno(aluno), HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAll/dto")
	public ResponseEntity<List<AlunoDTO>> saveAllAlunosDTO(@RequestBody List<AlunoDTO> alunoDTO) {
		return new ResponseEntity<>(alunoService.saveAllAlunosDTO(alunoDTO), HttpStatus.CREATED);
	}
		
	@PostMapping("/save/dto")
	public ResponseEntity<AlunoDTO> saveAlunoDTO(@RequestBody AlunoDTO alunoDTO) {
		System.out.println(alunoDTO.getNome());
		return new ResponseEntity<>(alunoService.saveAlunoDTO(alunoDTO), HttpStatus.CREATED);
	}

//	Delete
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Aluno> deleteAluno(@PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.deleteAluno(id), HttpStatus.OK);
	}
	
//	@DeleteMapping("/deleteAll/{id}")
//	public ResponseEntity<Aluno> deleteAllAlunos(@PathVariable Integer id) {
//		return new ResponseEntity<>(alunoService.deleteAllAlunos(id), HttpStatus.OK);
//	}
	
	@DeleteMapping("/delete/dto/{id}")
	public ResponseEntity<AlunoDTO> deleteAlunoDTO(@PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.deleteAlunoDTO(id), HttpStatus.OK);
	}

//	Update
	@PutMapping("/update/{id}")
	public ResponseEntity<Aluno> updateAluno(@RequestBody Aluno aluno, @PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.updateAluno(aluno, id), HttpStatus.OK);
	}
	
	@PutMapping("/update/dto/{id}")
	public ResponseEntity<AlunoDTO> updateAlunoDTO(@RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
		return new ResponseEntity<>(alunoService.updateAlunoDTO(alunoDTO, id), HttpStatus.OK);
	}
}
