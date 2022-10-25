package com.serratec.ecommerce.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;

import com.serratec.ecommerce.ecommerce.model.ItemPedido;
import com.serratec.ecommerce.ecommerce.model.Pedido;

public class PedidoDTO {

  private Long id;
  private Instant dataPedido;
  @NotBlank(message = "O Campo e Obrigatorio")
  private String dataEntrega;
  @NotBlank(message = "O Campo e Obrigatorio")
  private String dataEnvio;
  // private Status status;
  private ClienteDTO client;
  private List<ItemPedidoDTO> items = new ArrayList<>();
  private Double valorTotal;

  public PedidoDTO() {
  }

  public PedidoDTO(Long id, String dataEntrega, String dataEnvio, ClienteDTO client,
      Double valorTotal) {
    this.id = id;
    this.dataEntrega = dataEntrega;
    this.dataEnvio = dataEnvio;
    this.client = client;
    this.valorTotal = valorTotal;
  }

  public PedidoDTO(Pedido pedido) {
    this.id = pedido.getId();
    this.dataPedido = pedido.getDataPedido();
    this.dataEntrega = pedido.getDataEntrega();
    this.dataEnvio = pedido.getDataEnvio();
    this.client = new ClienteDTO(pedido.getCliente());
    this.valorTotal = pedido.getTotal();

    for (ItemPedido item : pedido.getItems()) {
      ItemPedidoDTO itemDTO = new ItemPedidoDTO(item);

      items.add(itemDTO);

    }

  }

  public Instant getDataPedido() {
    return this.dataPedido;
  }

  public void setDataPedido(Instant dataPedido) {
    this.dataPedido = dataPedido;
  }

  public void setItems(List<ItemPedidoDTO> items) {
    this.items = items;
  }

  public Double getValorTotal() {
    return this.valorTotal;
  }

  public void setValorTotal(Double valorTotal) {
    this.valorTotal = valorTotal;
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public ClienteDTO getClient() {
    return this.client;
  }

  public void setClient(ClienteDTO client) {
    this.client = client;
  }

  public List<ItemPedidoDTO> getItems() {
    return this.items;
  }

}