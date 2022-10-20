package com.serratec.ecommerce.ecommerce.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_produto")
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private BigDecimal qtdEstoque;
  private Instant dataCadastro;
  private BigDecimal valorUnitario;

  @OneToMany(mappedBy = "itemPedidoPk.produto")
  private Set<ItemPedido> items = new HashSet<>();

  public Produto() {
  }

  public Produto(Long id, String nome, BigDecimal qtdEstoque, Instant dataCadastro, BigDecimal valorUnitario,
      Set<ItemPedido> items) {
    this.id = id;
    this.nome = nome;
    this.qtdEstoque = qtdEstoque;
    this.dataCadastro = dataCadastro;
    this.valorUnitario = valorUnitario;
    this.items = items;
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

  public Set<ItemPedido> getItems() {
    return this.items;
  }

  public void setItems(Set<ItemPedido> items) {
    this.items = items;
  }

}