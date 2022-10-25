package com.serratec.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerce.ecommerce.model.ItemPedido;
import com.serratec.ecommerce.ecommerce.model.ItemPedidoPk;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, ItemPedidoPk> {

}