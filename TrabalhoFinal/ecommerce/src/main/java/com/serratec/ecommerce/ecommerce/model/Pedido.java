package com.serratec.ecommerce.ecommerce.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_pedido")
public class Pedido {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
  private Instant dataPedido;
  private String dataEntrega;
  private String dataEnvio;
  private Status status;
  private String valorTotal;

  @ManyToOne
  @JoinColumn(name = "cliente_id")

  private Cliente cliente;

  @OneToMany(mappedBy = "itemPedidoPk.pedido")
  private Set<ItemPedido> items = new HashSet<>();

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getDataPedido() {
    return this.dataPedido;
  }

  public void setDataPedido(Instant dataPedido) {
    this.dataPedido = dataPedido;
  }

  public String getDataEntrega() {
    return this.dataEntrega;
  }

  public void setDataEntrega(String dataEntrega) {
    this.dataEntrega = dataEntrega;
  }

  public String getDataEnvio() {
    return this.dataEnvio;
  }

  public void setDataEnvio(String dataEnvio) {
    this.dataEnvio = dataEnvio;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getValorTotal() {
    return this.valorTotal;
  }

  public void setValorTotal(String valorTotal) {
    this.valorTotal = valorTotal;
  }

  public Cliente getCliente() {
    return this.cliente;
  }

  public void setCliente(Cliente cliente) {
    this.cliente = cliente;
  }

  public Set<ItemPedido> getItems() {
    return this.items;
  }

  public void setItems(Set<ItemPedido> items) {
    this.items = items;
  }

}