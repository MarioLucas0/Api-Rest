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

import com.serratec.ecommerce.ecommerce.dto.ClienteDTO;
import com.serratec.ecommerce.ecommerce.service.ClienteService;

@RestController
@RequestMapping(value = "/cliente")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping(value = "/{id}")
  public ResponseEntity<ClienteDTO> findById(@PathVariable Long id) {
    ClienteDTO dto = clienteService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  public ResponseEntity<List<ClienteDTO>> findAll() {
    List<ClienteDTO> dto = clienteService.findAll();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  public ResponseEntity<ClienteDTO> insert(@Valid @RequestBody ClienteDTO clienteDto) {
    ClienteDTO dto = clienteService.insert(clienteDto);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
    return ResponseEntity.created(uri).body(dto);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<ClienteDTO> update(@Valid @PathVariable Long id, @RequestBody ClienteDTO productDto) {
    productDto = clienteService.update(productDto, id);
    return ResponseEntity.ok(productDto);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    clienteService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}