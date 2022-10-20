package com.serratec.ecommerce.ecommerce.dto;

public class ClienteDTO {

  private String email;
  private String nomeCompleto;
  private String cpf;
  private String dataNascimento;

  public ClienteDTO() {
  }

  public ClienteDTO(String email, String nomeCompleto, String cpf, String dataNascimento) {
    this.email = email;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNomeCompleto() {
    return this.nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getDataNascimento() {
    return this.dataNascimento;
  }

  public void setDataNascimento(String dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

}