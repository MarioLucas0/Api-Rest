package com.serratec.ecommerce.ecommerce.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.serratec.ecommerce.ecommerce.model.ItemPedido;
import com.serratec.ecommerce.ecommerce.model.Pedido;
import com.serratec.ecommerce.ecommerce.model.Status;

public class RelatorioDTO {

  private Instant dataPedido;
  private String dataEntrega;
  private String dataEnvio;
  private Status status;
  private ClienteDTO client;
  private List<ItemPedidoDTO> items = new ArrayList<>();
  private Double valorTotal;
  // private List<ItemPedido> item = new ArrayList<>();

  public RelatorioDTO(Pedido pedido) {

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

  public ClienteDTO getClient() {
    return this.client;
  }

  public void setClient(ClienteDTO client) {
    this.client = client;
  }

  public List<ItemPedidoDTO> getItems() {
    return this.items;
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

  @Override
  public String toString() {
    return "\nData do Pedido = " + getDataPedido() +
        "\nData de Entrega = " + getDataEntrega() +
        "\nData de Envio = " + getDataEnvio() +
        "\nValor Total do Pedido = " + getValorTotal() +
        "\n                      " +
        "\nItems do Pedido  " + getItems().toString();

  }

}/*  */