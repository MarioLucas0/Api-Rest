package com.blibiotecaex.blibioteca.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.blibiotecaex.blibioteca.dto.AutorDTO;
import com.blibiotecaex.blibioteca.entities.Autor;
import com.blibiotecaex.blibioteca.services.AutorService;

@RestController
@RequestMapping("/autors")
public class AutorController {

  @Autowired
  private AutorService autorService;

  @GetMapping
  public ResponseEntity<List<Autor>> getAllAutors() {
    return new ResponseEntity<>(autorService.getAllAutor(),
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
  public ResponseEntity<AutorDTO> inserir(@Valid @RequestBody AutorDTO autorDTO) {
    autorDTO = autorService.insert(autorDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorDTO.getId()).toUri();
    return ResponseEntity.created(uri).body(autorDTO);
  }

  /*
   * @PutMapping("/{id}")
   * public ResponseEntity<Livro> updateLivro(@RequestBody Livro
   * livro, @PathVariable Long id) {
   * return new ResponseEntity<>(livroService.updateLivro(livro, id),
   * HttpStatus.OK);
   * }
   */
  /*
   * @DeleteMapping("/{id}")
   * public ResponseEntity<Livro> deleteLivro(@PathVariable Long id) {
   * Livro livro = livroService.getLivroById(id);
   * if (null == livro)
   * return new ResponseEntity<>(livro,
   * HttpStatus.NOT_FOUND);
   * else
   * return new ResponseEntity<>(livroService.deleteLivro(id),
   * HttpStatus.OK);
   * }
   */
}
