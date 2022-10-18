package com.blibiotecaex.blibioteca.dto;

import com.blibiotecaex.blibioteca.entities.Categoria;

public class CategoriaDTO {

  private Long id;
  private String nome;

  public CategoriaDTO(Long id, String nome) {
    this.id = id;
    this.nome = nome;

  }

  public CategoriaDTO(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return this.nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}