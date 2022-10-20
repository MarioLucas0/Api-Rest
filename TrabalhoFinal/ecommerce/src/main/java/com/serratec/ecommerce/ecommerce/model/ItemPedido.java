package com.serratec.ecommerce.ecommerce.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Integer quantidade;
  private BigDecimal valorVenda;
  private BigDecimal percentualDesconto;
  private BigDecimal valorLiquido;

  @EmbeddedId
  private ItemPedidoPk itemPedidoPk = new ItemPedidoPk();

  public ItemPedido() {
  }

  public ItemPedido(Long id, Integer quantidade, BigDecimal valorVenda, BigDecimal percentualDesconto,
      BigDecimal valorLiquido, Pedido pedido, Produto produto) {
    this.id = id;
    this.quantidade = quantidade;
    this.valorVenda = valorVenda;
    this.percentualDesconto = percentualDesconto;
    this.valorLiquido = valorLiquido;
    itemPedidoPk.setPedido(pedido);
    itemPedidoPk.setProduto(produto);
  }

  public Pedido getPedido() {
    return itemPedidoPk.getPedido();
  }

  public void setPedido(Pedido pedido) {
    itemPedidoPk.setPedido(pedido);
  }

  public Produto getProduto() {
    return itemPedidoPk.getProduto();
  }

  public void setProduto(Produto produto) {
    itemPedidoPk.setProduto(produto);
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public BigDecimal getValorVenda() {
    return this.valorVenda;
  }

  public void setValorVenda(BigDecimal valorVenda) {
    this.valorVenda = valorVenda;
  }

  public BigDecimal getPercentualDesconto() {
    return this.percentualDesconto;
  }

  public void setPercentualDesconto(BigDecimal percentualDesconto) {
    this.percentualDesconto = percentualDesconto;
  }

  public BigDecimal getValorLiquido() {
    return this.valorLiquido;
  }

  public void setValorLiquido(BigDecimal valorLiquido) {
    this.valorLiquido = valorLiquido;
  }

}