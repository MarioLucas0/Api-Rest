package com.serratec.ecommerce.ecommerce.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.serratec.ecommerce.ecommerce.model.Categoria;

public class CategoriaDTO {

  private Long id;
  @NotBlank(message = "Digite um nome para a categoria")
  @Size(min = 7, max = 30)
  private String nome;
  @NotBlank(message = "Digite um descrição para a categoria")
  @Size(min = 10, max = 150)
  private String descricao;

  public CategoriaDTO() {
  }

  public CategoriaDTO(Long id, String nome, String descricao) {
    this.id = id;
    this.nome = nome;
    this.descricao = descricao;
  }

  public CategoriaDTO(Categoria categoria) {
    this.nome = categoria.getNome();
    this.descricao = categoria.getDescricao();
    this.id = categoria.getId();
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

  public String getDescricao() {
    return this.descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

}