package com.blibiotecaex.blibioteca.dto;

import com.blibiotecaex.blibioteca.entities.Associado;

public class AssociadoDTO {

  private Long id;
  private String nome;
  private String telefone;
  private String cpf;
  private String logradouro;
  private String numero;
  private String complemento;
  private String bairro;
  private String cidade;
  private String estado;

  public AssociadoDTO(Long id, String nome, String telefone, String cpf, String logradouro, String numero,
      String complemento, String bairro, String cidade, String estado) {
    this.id = id;
    this.nome = nome;
    this.telefone = telefone;
    this.cpf = cpf;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cidade = cidade;
    this.estado = estado;
  }

  public AssociadoDTO(Associado associado) {
    this.id = associado.getId();
    this.nome = associado.getNome();
    this.telefone = associado.getTelefone();
    this.cpf = associado.getCpf();
    this.logradouro = associado.getLogradouro();
    this.numero = associado.getNumero();
    this.complemento = associado.getComplemento();
    this.bairro = associado.getBairro();
    this.cidade = associado.getCidade();
    this.estado = associado.getEstado();
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

  public String getTelefone() {
    return this.telefone;
  }

  public void setTelefone(String telefone) {
    this.telefone = telefone;
  }

  public String getCpf() {
    return this.cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getLogradouro() {
    return this.logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
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

}
