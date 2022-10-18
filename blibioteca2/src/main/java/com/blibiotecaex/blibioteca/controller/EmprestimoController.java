package com.blibiotecaex.blibioteca.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blibiotecaex.blibioteca.dto.EmprestimoDTO;
import com.blibiotecaex.blibioteca.entities.Emprestimo;
import com.blibiotecaex.blibioteca.services.EmprestimoService;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

  @Autowired
  EmprestimoService emprestimoService;

  @GetMapping
  public ResponseEntity<List<Emprestimo>> getAllEmprestimo() {
    return new ResponseEntity<>(emprestimoService.getAllEmprestimos(),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<EmprestimoDTO> inserir(@RequestBody EmprestimoDTO li) {
    li = emprestimoService.insert(li);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(li.getId()).toUri();
    return ResponseEntity.created(uri).body(li);

  }

}