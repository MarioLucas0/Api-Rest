package com.serratec.ecommerce.ecommerce.dto;

import java.math.BigDecimal;
import java.time.Instant;

import com.serratec.ecommerce.ecommerce.model.Produto;

public class ProdutoDTO {
  private Long id;
  private String nome;
  private BigDecimal qtdEstoque;
  private Instant dataCadastro;
  private BigDecimal valorUnitario;

  public ProdutoDTO() {
  }

  public ProdutoDTO(Long id, String nome, BigDecimal qtdEstoque, Instant dataCadastro, BigDecimal valorUnitario) {
    this.id = id;
    this.nome = nome;
    this.qtdEstoque = qtdEstoque;
    this.dataCadastro = dataCadastro;
    this.valorUnitario = valorUnitario;
  }

  public ProdutoDTO(Produto entity) {
    id = entity.getId();
    nome = entity.getNome();
    qtdEstoque = entity.getQtdEstoque();
    dataCadastro = entity.getDataCadastro();
    valorUnitario = entity.getValorUnitario();
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

  public BigDecimal getQtdEstoque() {
    return this.qtdEstoque;
  }

  public void setQtdEstoque(BigDecimal qtdEstoque) {
    this.qtdEstoque = qtdEstoque;
  }

  public Instant getDataCadastro() {
    return this.dataCadastro;
  }

  public void setDataCadastro(Instant dataCadastro) {
    this.dataCadastro = dataCadastro;
  }

  public BigDecimal getValorUnitario() {
    return this.valorUnitario;
  }

  public void setValorUnitario(BigDecimal valorUnitario) {
    this.valorUnitario = valorUnitario;
  }

}