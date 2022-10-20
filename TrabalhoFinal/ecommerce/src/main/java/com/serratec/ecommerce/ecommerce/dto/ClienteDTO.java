package com.serratec.ecommerce.ecommerce.dto;

import com.serratec.ecommerce.ecommerce.model.Cliente;

public class ClienteDTO {

  private Long id;
  private String email;
  private String nomeCompleto;
  private String cpf;
  private String dataNascimento;
  private EnderecoDTO endereco;

  public ClienteDTO() {
  }

  public ClienteDTO(String email, String nomeCompleto, String cpf, String dataNascimento) {
    this.email = email;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
  }

  public ClienteDTO(Cliente cliente) {
    email = cliente.getEmail();
    nomeCompleto = cliente.getNomeCompleto();
    cpf = cliente.getCpf();
    dataNascimento = cliente.getDataNascimento();
    endereco = new EnderecoDTO(cliente.getEndereco());

  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public EnderecoDTO getEndereco() {
    return this.endereco;
  }

  public void setEndereco(EnderecoDTO endereco) {
    this.endereco = endereco;
  }

}