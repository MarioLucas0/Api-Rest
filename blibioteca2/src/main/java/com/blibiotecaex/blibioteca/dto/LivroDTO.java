package com.blibiotecaex.blibioteca.dto;

import java.time.Instant;

import com.blibiotecaex.blibioteca.entities.Livro;

public class LivroDTO {

  /* Post aninhado */

  private Long id;
  private String name;
  private String isbn;
  private Instant datapublicacao;
  private AutorDTO autor;

  public LivroDTO() {
  }

  public LivroDTO(Long id, String name, String isbn, Instant datapublicacao, AutorDTO autorDto) {
    this.id = id;
    this.name = name;
    this.isbn = isbn;
    this.datapublicacao = datapublicacao;
    this.autor = autorDto;
  }

  public LivroDTO(Livro livro) {
    this.id = livro.getId();
    this.name = livro.getNome();
    this.isbn = livro.getIsbn();
    this.datapublicacao = livro.getDatapublicacao();
    this.autor = new AutorDTO(livro.getAutor());

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

  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public Instant getDatapublicacao() {
    return this.datapublicacao;
  }

  public void setDatapublicacao(Instant datapublicacao) {
    this.datapublicacao = datapublicacao;
  }

  public AutorDTO getAutorDto() {
    return this.autor;
  }

  public void setAutorDto(AutorDTO autorDto) {
    this.autor = autorDto;
  }

}
