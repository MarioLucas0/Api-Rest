package com.serratec.ecommerce.ecommerce.dto;

import java.time.LocalDate;

import com.serratec.ecommerce.ecommerce.model.Cliente;
import com.serratec.ecommerce.ecommerce.model.Endereco;

public class ClienteDTO {

  private Long id;
  private String email;
  private String nomeCompleto;
  private String cpf;
  private LocalDate dataNascimento;
  private Endereco endereco;

  public ClienteDTO() {
  }

  public ClienteDTO(Long id, String email, String nomeCompleto, String cpf, LocalDate dataNascimento,
      Endereco endereco) {
    this.id = id;
    this.email = email;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.dataNascimento = dataNascimento;
    this.endereco = endereco;
  }

  public ClienteDTO(Cliente cliente) {
    id = cliente.getId();
    email = cliente.getEmail();
    nomeCompleto = cliente.getNomeCompleto();
    cpf = cliente.getCpf();
    dataNascimento = cliente.getDataNascimento();
    endereco = cliente.getEndereco();

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

  public LocalDate getDataNascimento() {
    return this.dataNascimento;
  }

  public void setDataNascimento(LocalDate dataNascimento) {
    this.dataNascimento = dataNascimento;
  }

  public Endereco getEndereco() {
    return this.endereco;
  }

  public void setEndereco(Endereco endereco) {
    this.endereco = endereco;
  }

}