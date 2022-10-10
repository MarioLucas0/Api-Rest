package br.com.residencia.blibioteca.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.residencia.blibioteca.entity.Aluno;
import br.com.residencia.blibioteca.service.AlunoService;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

  @Autowired
  AlunoService alunoService;

  @GetMapping
  public ResponseEntity<List<Aluno>> getAllAlunos() {
    return new ResponseEntity<>(alunoService.getAllAlunos(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Aluno> getAlunoById(@PathVariable Integer id) {
    return new ResponseEntity<>(alunoService.getAlunoById(id), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Aluno> saveAluno(@RequestBody Aluno aluno) {
    return new ResponseEntity<>(alunoService.saveAluno(aluno), HttpStatus.OK);

  }

}