package com.blibiotecaex.blibioteca.dto;

import com.blibiotecaex.blibioteca.entities.Autor;

public class AutorDTO {

  private Long id;
  private String name;

  public AutorDTO(Long id, String name) {
    this.id = id;
    this.name = name;
  }

  public AutorDTO(Autor autor) {
    this.id = autor.getId();
    this.name = autor.getNome();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
