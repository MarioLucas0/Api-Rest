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

import com.blibiotecaex.blibioteca.dto.AssociadoDTO;
import com.blibiotecaex.blibioteca.entities.Associado;
import com.blibiotecaex.blibioteca.services.AssociadoService;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

  @Autowired
  AssociadoService associadoService;

  @GetMapping
  public ResponseEntity<List<Associado>> getAllAssociados() {
    return new ResponseEntity<>(associadoService.getAllAssociados(),
        HttpStatus.OK);
  }
  /*
   * @GetMapping("/{id}")
   * public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
   * Livro livro = autorService.getLivroById(id);
   * if (null != livro)
   * return new ResponseEntity<>(livro,
   * HttpStatus.OK);
   * else
   * return new ResponseEntity<>(livro,
   * HttpStatus.NOT_FOUND);
   * }
   */

  @PostMapping
  public ResponseEntity<AssociadoDTO> inserir(@RequestBody AssociadoDTO associadoDTO) {
    associadoDTO = associadoService.insert(associadoDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associadoDTO.getId())
        .toUri();
    return ResponseEntity.created(uri).body(associadoDTO);
  }
}