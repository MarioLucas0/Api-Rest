package com.serratec.ecommerce.ecommerce.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serratec.ecommerce.ecommerce.dto.ProdutoDTO;
import com.serratec.ecommerce.ecommerce.service.ProdutoService;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "Bearer Authentication")
@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

  @Autowired
  private ProdutoService produtoService;

  @GetMapping(value = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Retorna o produto cadastrado"),
      @ApiResponse(responseCode = "400", description = "Imagem não foi selecionada"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "415", description = "Tipo de arquivo não suportado"),
      @ApiResponse(responseCode = "422", description = "Categoria não encontrada ou arquivo invalido"),
      @ApiResponse(responseCode = "404", description = "produto não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<ProdutoDTO> findById(@PathVariable Long id) {
    ProdutoDTO dto = produtoService.findById(id);
    return ResponseEntity.ok(dto);
  }

  @GetMapping
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Retorna o cliente cadastrado"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "422", description = "Você credencias já cadastradas no banco de dados"),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })

  public ResponseEntity<List<ProdutoDTO>> findAll() {
    return new ResponseEntity<>(produtoService.listar(),
        HttpStatus.OK);
  }

  @PostMapping(value = "/insert", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
  @ApiResponses(value = {
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "422", description = "Categoria não encontrada ou arquivo invalido"),
      @ApiResponse(responseCode = "404", description = "produto não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação"),

  })

  public ResponseEntity<ProdutoDTO> insert(@Valid @RequestPart("produto") String produtoTxt,
      @RequestPart("filename") MultipartFile file) throws IOException {

    ProdutoDTO produtoDTO = produtoService.insert(produtoTxt, file);

    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produtoDTO.getId())
        .toUri();
    return ResponseEntity.created(uri).body(produtoDTO);

  }

  @PutMapping(value = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Retorna o produto cadastrado"),
      @ApiResponse(responseCode = "400", description = "Imagem não foi selecionada"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "415", description = "Tipo de arquivo não suportado"),
      @ApiResponse(responseCode = "422", description = "Categoria não encontrada ou arquivo invalido"),
      @ApiResponse(responseCode = "404", description = "produto não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<ProdutoDTO> update(@Valid @PathVariable Long id, @RequestBody ProdutoDTO productDto) {
    productDto = produtoService.update(productDto, id);
    return ResponseEntity.ok(productDto);
  }

  @DeleteMapping(value = "/{id}")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Deletado com sucesso"),
      @ApiResponse(responseCode = "401", description = "Erro de autenticação"),
      @ApiResponse(responseCode = "403", description = "Você não tem permissão para o recurso"),
      @ApiResponse(responseCode = "404", description = "Cliente não encontrado"),
      @ApiResponse(responseCode = "500", description = "Erro na aplicação")
  })
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    produtoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}