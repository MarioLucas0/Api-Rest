package com.blibiotecaex.blibioteca.dto;

import java.time.Instant;

import com.blibiotecaex.blibioteca.entities.Livro;

public class LivroDTO2 {
  private Long id;
  private String name;
  private String isbn;
  private Instant datapublicacao;
  private Long autorId;
  private Long categoriaId;

  public LivroDTO2(Long id, String name, String isbn, Instant datapublicacao, Long autorId, Long categoriaId) {
    this.id = id;
    this.name = name;
    this.isbn = isbn;
    this.datapublicacao = datapublicacao;
    this.autorId = autorId;
    this.categoriaId = categoriaId;
  }

  public LivroDTO2(Livro livro) {
    this.id = livro.getId();
    this.name = livro.getNome();
    this.isbn = livro.getIsbn();
    this.datapublicacao = livro.getDatapublicacao();
    this.autorId = livro.getAutor().getId();
    this.categoriaId = livro.getCategoria().getId();

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

  public Long getAutorid() {
    return this.autorId;
  }

  public void setAutorid(Long autorid) {
    this.autorId = autorid;
  }

  public Long getCategoriaId() {
    return this.categoriaId;
  }

  public void setCategoriaId(Long categoriaId) {
    this.categoriaId = categoriaId;
  }

}