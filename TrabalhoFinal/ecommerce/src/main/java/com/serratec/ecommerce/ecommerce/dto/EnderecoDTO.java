package com.serratec.ecommerce.ecommerce.dto;

import com.serratec.ecommerce.ecommerce.model.Endereco;

public class EnderecoDTO {

  private Long id;
  private String rua;
  private String bairro;
  private String cidade;
  private String numero;
  private String complemento;
  private String uf;

  public EnderecoDTO(Endereco endereco) {
    this.rua = endereco.getRua();
    this.bairro = endereco.getBairro();
    this.cidade = endereco.getCidade();
    this.numero = endereco.getNumero();
    this.complemento = endereco.getComplemento();
    this.uf = endereco.getUf();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRua() {
    return this.rua;
  }

  public void setRua(String rua) {
    this.rua = rua;
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

  public String getNumero() {
    return this.numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getUf() {
    return this.uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }

}