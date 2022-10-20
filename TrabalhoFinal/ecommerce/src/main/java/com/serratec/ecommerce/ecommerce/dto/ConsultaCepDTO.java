package com.serratec.ecommerce.ecommerce.dto;

public class ConsultaCepDTO {

  private String logradouro;
  private String bairro;
  private String localidade;
  private String numero;
  private String complemento;
  private String uf;

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getBairro() {
    return this.bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getLocalidade() {
    return this.localidade;
  }

  public void setLocalidade(String localidade) {
    this.localidade = localidade;
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