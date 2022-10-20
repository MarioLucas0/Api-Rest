package com.serratec.ecommerce.ecommerce.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.serratec.ecommerce.ecommerce.dto.CategoriaDTO;
import com.serratec.ecommerce.ecommerce.service.CategoriaService;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaController {

  @Autowired
  CategoriaService categoriaService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<CategoriaDTO> findById(@PathVariable Long id) {
    CategoriaDTO dto = categoriaService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  public ResponseEntity<List<CategoriaDTO>> findAll() {
    List<CategoriaDTO> dto = categoriaService.findAll();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<CategoriaDTO> insert(@Valid @RequestBody CategoriaDTO categoriaDTO) {
    CategoriaDTO dto = categoriaService.insert(categoriaDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<CategoriaDTO> update(@Valid @PathVariable Long id, @RequestBody CategoriaDTO categoriaDto) {
    categoriaDto = categoriaService.update(categoriaDto, id);
    return ResponseEntity.ok(categoriaDto);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    categoriaService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}