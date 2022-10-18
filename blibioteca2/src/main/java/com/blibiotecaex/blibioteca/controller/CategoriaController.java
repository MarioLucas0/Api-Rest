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

import com.blibiotecaex.blibioteca.dto.CategoriaDTO;
import com.blibiotecaex.blibioteca.entities.Categoria;
import com.blibiotecaex.blibioteca.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

  @Autowired
  CategoriaService categoriaService;

  @GetMapping
  public ResponseEntity<List<Categoria>> getAllAutors() {
    return new ResponseEntity<>(categoriaService.getAllCategorias(),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<CategoriaDTO> inserir(@Valid @RequestBody CategoriaDTO categoriaDto) {
    categoriaDto = categoriaService.insert(categoriaDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoriaDto.getId())
        .toUri();
    return ResponseEntity.created(uri).body(categoriaDto);
  }

}