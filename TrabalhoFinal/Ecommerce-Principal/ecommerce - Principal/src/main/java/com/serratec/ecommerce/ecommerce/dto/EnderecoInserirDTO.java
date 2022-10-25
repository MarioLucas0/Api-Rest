package com.serratec.ecommerce.ecommerce.dto;

import com.serratec.ecommerce.ecommerce.model.Endereco;

public class EnderecoInserirDTO {

  private Integer numero;
  private String complemento;
  private String cep;

  public EnderecoInserirDTO() {
  }

  public EnderecoInserirDTO(Endereco endereco) {
    this.numero = endereco.getNumero();
    this.complemento = endereco.getComplemento();
    this.cep = endereco.getCep();
  }

  public Integer getNumero() {
    return this.numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return this.complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getCep() {
    return this.cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

}