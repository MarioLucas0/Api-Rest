package com.serratec.ecommerce.ecommerce.dto;

import com.serratec.ecommerce.ecommerce.model.Endereco;

public class EnderecoDTO {

  private Long id;
  private String cep;
  private String logradouro;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;
  private Integer numero;

  public EnderecoDTO(Endereco endereco) {
    this.id = endereco.getId();
    this.cep = endereco.getCep();
    this.logradouro = endereco.getLogradouro();
    this.complemento = endereco.getComplemento();
    this.bairro = endereco.getBairro();
    this.cidade = endereco.getLocalidade();
    this.estado = endereco.getUf();
    this.numero = endereco.getNumero();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return this.cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return this.estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public Integer getNumero() {
    return this.numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

}