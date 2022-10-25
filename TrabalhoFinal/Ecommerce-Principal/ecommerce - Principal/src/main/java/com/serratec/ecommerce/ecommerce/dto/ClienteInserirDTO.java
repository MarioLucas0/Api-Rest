package com.serratec.ecommerce.ecommerce.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import com.serratec.ecommerce.ecommerce.model.Cliente;

public class ClienteInserirDTO implements Serializable {

  private static final long serialVersionUID = 1L;

  private Long id;

  @NotBlank(message = "você deve digitar um email valido")
  @Email(message = "digite um email valido")
  private String email;

  @NotBlank(message = "você deve digitar um nome")
  @Column(name = "nome_completo")
  private String nomeCompleto;

  @NotBlank(message = "você deve digitar um cpf valido")
  private String cpf;

  @NotNull(message = "você deve digitar uma data correta")
  @Past
  private LocalDate dataNascimento;

  @NotNull(message = "você deve completar os dados corretamente")
  private EnderecoInserirDTO endereco;

  public ClienteInserirDTO() {
  }

  public ClienteInserirDTO(Cliente cliente) {
    this.id = cliente.getId();
    this.email = cliente.getEmail();
    this.nomeCompleto = cliente.getNomeCompleto();
    this.cpf = cliente.getCpf();
    this.dataNascimento = cliente.getDataNascimento();
    this.endereco = new EnderecoInserirDTO(cliente.getEndereco());
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

  public EnderecoInserirDTO getEndereco() {
    return this.endereco;
  }

  public void setEndereco(EnderecoInserirDTO endereco) {
    this.endereco = endereco;
  }

}
