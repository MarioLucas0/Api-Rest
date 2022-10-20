package com.blibiotecaex.blibioteca.controller;

import java.net.URI;
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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blibiotecaex.blibioteca.dto.LivroDTO2;
import com.blibiotecaex.blibioteca.entities.Livro;
import com.blibiotecaex.blibioteca.services.LivroService;

@RestController
@RequestMapping("/livros")
public class LivroController {

  @Autowired
  private LivroService livroService;

  @GetMapping
  public ResponseEntity<List<LivroDTO2>> getAllEditoras() {
    return new ResponseEntity<>(livroService.getAllLivros(),
        HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Livro> getLivroById(@PathVariable Long id) {
    Livro livro = livroService.getLivroById(id);
    if (null != livro)
      return new ResponseEntity<>(livro,
          HttpStatus.OK);
    else
      return new ResponseEntity<>(livro,
          HttpStatus.NOT_FOUND);
  }

  /* Post aninhado */
  /*
   * @PostMapping
   * public ResponseEntity<LivroDTO> inserir(@RequestBody LivroDTO li) {
   * li = livroService.insert(li);
   * URI uri =
   * ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand
   * (li.getId()).toUri();
   * return ResponseEntity.created(uri).body(li);
   * 
   * }
   */

  @PostMapping
  public ResponseEntity<LivroDTO2> inserir(@RequestBody LivroDTO2 li) {
    li = livroService.insert(li);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(li.getId()).toUri();
    return ResponseEntity.created(uri).body(li);

  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<LivroDTO2> update(@PathVariable Long id, @RequestBody LivroDTO2 livroDTO) {
    livroDTO = livroService.update(livroDTO, id);
    return ResponseEntity.ok(livroDTO);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    livroService.deleteById(id);
    return ResponseEntity.noContent().build();
  }

}
