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

import com.serratec.ecommerce.ecommerce.dto.PedidoDTO;
import com.serratec.ecommerce.ecommerce.service.PedidoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(value = "/pedido")
public class PedidoController {

  @Autowired
  PedidoService pedidoService;

  @GetMapping(value = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retorna todos os pedidos"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
    PedidoDTO dto = pedidoService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Retorna Pedido do id referenciado"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<List<PedidoDTO>> findAll() {
    List<PedidoDTO> dto = pedidoService.findAll();
    return ResponseEntity.ok(dto);
  }

  @PostMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Retorna o categoria cadastrado"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "422", description = "Você credencias já cadastradas no banco de dados"),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<PedidoDTO> insert(@Valid @RequestBody PedidoDTO PedidoDTO) {
    PedidoDTO = pedidoService.insert(PedidoDTO);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(PedidoDTO.getId()).toUri();
    return ResponseEntity.created(uri).body(PedidoDTO);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PedidoDTO> update(@Valid @PathVariable Long id, @RequestBody PedidoDTO productDto) {
    productDto = pedidoService.update(productDto, id);
    return ResponseEntity.ok(productDto);
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    pedidoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}