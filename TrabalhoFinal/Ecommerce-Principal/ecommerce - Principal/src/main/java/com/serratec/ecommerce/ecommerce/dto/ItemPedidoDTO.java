package com.serratec.ecommerce.ecommerce.dto;

import com.serratec.ecommerce.ecommerce.model.ItemPedido;

public class ItemPedidoDTO {

  private Long produtoId;
  private String name;
  private Integer quantidade;
  private Double precoVenda;
  private Integer percentualDesconto;

  public ItemPedidoDTO() {
  }

  public ItemPedidoDTO(Long produtoId, String name, Integer quantidade, Double precoVenda,
      Integer percentualDesconto) {
    this.produtoId = produtoId;
    this.name = name;
    this.quantidade = quantidade;
    this.precoVenda = precoVenda;
    this.percentualDesconto = percentualDesconto;

  }

  public ItemPedidoDTO(ItemPedido itemPedido) {
    produtoId = itemPedido.getProduto().getId();
    name = itemPedido.getProduto().getNome();
    quantidade = itemPedido.getQuantidade();
    precoVenda = itemPedido.getProduto().getValorUnitario();
    percentualDesconto = itemPedido.getPercentualDesconto();
    /* Id ItemPedido */
    itemPedido.setId(itemPedido.getPedido().getId());
  }

  public Long getProdutoId() {
    return this.produtoId;
  }

  public void setProdutoId(Long produtoId) {
    this.produtoId = produtoId;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  @Override
  public String toString() {
    return "Codigo do Produto = " + getProdutoId() +
        "\nNome do Produto = " + getName() +
        "\nQuantidade de Produtos = " + getQuantidade() +
        "\nPreço de Venda do Produto = " + getPrecoVenda() +
        "\nPercentual de Desconto = " + getPercentualDesconto() + "%\n";

  }

}