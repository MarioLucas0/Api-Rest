package com.serratec.ecommerce.ecommerce.controller;

import java.net.URI;

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

import com.serratec.ecommerce.ecommerce.dto.ProdutoDTO;
import com.serratec.ecommerce.ecommerce.service.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
    ProdutoDTO dto = produtoService.findById(id);
    return ResponseEntity.ok(dto);
  }

  /*
   * @GetMapping
   * public ResponseEntity<Page<ProductDTO>> findAll(Pageable pageable) {
   * Page<ProductDTO> dto = service.findAll(pageable);
   * return ResponseEntity.ok(dto);
   * }
   */

  @PostMapping
  public ResponseEntity<ProdutoDTO> insert(@Valid @RequestBody ProdutoDTO productDto) {
    ProdutoDTO dto = produtoService.insert(productDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Long id, @RequestBody ProdutoDTO productDto) {
    productDto = produtoService.update(productDto, id);
    return ResponseEntity.ok(productDto);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    produtoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}