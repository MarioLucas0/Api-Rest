package com.serratec.ecommerce.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerce.ecommerce.model.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}