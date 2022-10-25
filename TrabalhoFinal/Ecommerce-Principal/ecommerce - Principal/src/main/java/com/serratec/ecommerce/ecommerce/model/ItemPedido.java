package com.serratec.ecommerce.ecommerce.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_item_pedido")
public class ItemPedido implements Serializable {

  private Long Id;
  private Integer quantidade;
  private Double precoVenda;
  private Integer percentualDesconto;
  private Double valorBrutoz;
  private Double valorLiquidoz;

  @EmbeddedId
  private ItemPedidoPk id = new ItemPedidoPk();

  public ItemPedido() {
  }

  public ItemPedido(Produto produto, Pedido pedido, Integer quantidade, Double precoVenda, Integer percentualDesconto) {
    id.setPedido(pedido);
    id.setProduto(produto);
    this.quantidade = quantidade;
    this.precoVenda = precoVenda; // produto.getValorUnitario();
    this.percentualDesconto = (quantidade > 30) ? 20 : 10;

  }

  public Double getValorBrutoz() {
    return this.valorBrutoz;
  }

  public void setValorBrutoz(Double valorBrutoz) {
    this.valorBrutoz = valorBrutoz;
  }

  public Double getValorLiquidoz() {
    return this.valorLiquidoz;
  }

  public void setValorLiquidoz(Double valorLiquidoz) {
    this.valorLiquidoz = valorLiquidoz;
  }

  public Long getId() {
    return this.Id;
  }

  public void setId(Long Id) {
    this.Id = Id;
  }

  public Pedido getPedido() {
    return id.getPedido();
  }

  public void setPedido(Pedido pedido) {
    id.setPedido(pedido);
  }

  public Produto getProduto() {
    return id.getProduto();
  }

  public void setProduto(Produto produto) {
    id.setProduto(produto);
  }

  public Integer getQuantidade() {
    return this.quantidade;
  }

  public void setQuantidade(Integer quantidade) {
    this.quantidade = quantidade;
  }

  public Double getPrecoVenda() {
    return this.precoVenda;
  }

  public void setPrecoVenda(Double precoVenda) {
    this.precoVenda = precoVenda;
  }

  public Integer getPercentualDesconto() {
    return this.percentualDesconto;
  }

  public void setPercentualDesconto(Integer percentualDesconto) {
    this.percentualDesconto = percentualDesconto;
  }

  public Double getValorLiquido() {
    return (precoVenda * quantidade - percentualDesconto * (precoVenda * quantidade) / 100);
  }

  public Double getValorBruto() {
    return precoVenda * quantidade;
  }

}