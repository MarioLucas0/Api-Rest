package com.serratec.ecommerce.ecommerce.service;

import java.time.Instant;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.serratec.ecommerce.ecommerce.config.MailConfig;
import com.serratec.ecommerce.ecommerce.dto.ItemPedidoDTO;
import com.serratec.ecommerce.ecommerce.dto.PedidoDTO;
import com.serratec.ecommerce.ecommerce.dto.RelatorioDTO;
import com.serratec.ecommerce.ecommerce.model.Cliente;
import com.serratec.ecommerce.ecommerce.model.ItemPedido;
import com.serratec.ecommerce.ecommerce.model.Pedido;
import com.serratec.ecommerce.ecommerce.model.Produto;
import com.serratec.ecommerce.ecommerce.model.Status;
import com.serratec.ecommerce.ecommerce.repository.ClienteRepository;
import com.serratec.ecommerce.ecommerce.repository.ItemPedidoRepository;
import com.serratec.ecommerce.ecommerce.repository.PedidoRepository;
import com.serratec.ecommerce.ecommerce.repository.ProdutoRepository;
import com.serratec.ecommerce.ecommerce.service.exceptions.DatabaseExcption;
import com.serratec.ecommerce.ecommerce.service.exceptions.QuantidadeException;
import com.serratec.ecommerce.ecommerce.service.exceptions.ResourceNotFoundException;

@Service
public class PedidoService {

  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Autowired
  ClienteRepository clienteRepository;

  @Autowired
  private PedidoRepository pedidoRepository;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Autowired
  private MailConfig mailConfig;

  public PedidoDTO findById(Long id) {

    Pedido pedido = pedidoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Pedido nao econtrado"));
    PedidoDTO dto = new PedidoDTO(pedido);
    return dto;

  }

  public List<PedidoDTO> findAll() {
    List<Pedido> result = pedidoRepository.findAll();
    return result.stream().map(x -> new PedidoDTO(x)).toList();
  }

  @Transactional
  public PedidoDTO insert(PedidoDTO pedidoDTO) {

    Pedido pedido = new Pedido();
    Cliente cliente = clienteRepository.getReferenceById(pedidoDTO.getClient().getId());

    for (ItemPedidoDTO itemDTO : pedidoDTO.getItems()) {

      Produto produto = produtoRepository.getReferenceById(itemDTO.getProdutoId());
      ItemPedido item = new ItemPedido(produto, pedido, itemDTO.getQuantidade(), produto.getValorUnitario(),
          itemDTO.getPercentualDesconto());

      /* item.setValorBruto(); */
      /*
       * item.setValorBruto(produto.getValorUnitario() * itemDTO.getQuantidade());
       * item.setValorLiquido(produto.getValorUnitario() * itemDTO.getQuantidade()
       * - (item.getPercentualDesconto() * (produto.getValorUnitario() *
       * itemDTO.getQuantidade()) / 100));
       * 
       * 
       */

      pedido.getItems().add(item);

      item.setValorBrutoz(item.getValorBruto());
      item.setValorLiquidoz(item.getValorLiquido());
      if (item.getProduto().getQtdEstoque() < item.getQuantidade()) {
        throw new QuantidadeException(
            "Quantidade de produtos e superior a quantidade no estoque digite um quantidade menor");
      } else {
        item.getProduto().setQtdEstoque(item.getProduto().getQtdEstoque() - item.getQuantidade());
      }

    }

    pedido.setDataEntrega(pedidoDTO.getDataEntrega());
    pedido.setDataEnvio(pedidoDTO.getDataEnvio());
    pedido.setValorTotal(pedido.getTotal());
    pedido.setDataPedido(Instant.now());
    pedido.setStatus(Status.DELIVERED);
    pedido.setCliente(cliente);

    pedidoRepository.save(pedido);
    itemPedidoRepository.saveAll(pedido.getItems());

    RelatorioDTO relatorio = new RelatorioDTO(pedido);
    mailConfig.sendEmail(cliente.getEmail(), "Dados do Pedido",
        relatorio.toString());

    return new PedidoDTO(pedido);
  }
  /*
   * @Transactional
   * public PedidoDTO insert(PedidoDTO pedidoDTO) {
   * 
   * Pedido entity = new Pedido();
   * 
   * Cliente cliente =
   * clienteRepository.getReferenceById(pedidoDTO.getClient().getId());
   * 
   * for (ItemPedidoDTO itemDTO : pedidoDTO.getItems()) {
   * Produto produto = produtoRepository.getReferenceById(itemDTO.getProdutoId());
   * ItemPedido item = new ItemPedido(produto, entity, itemDTO.getQuantidade(),
   * itemDTO.getPrecoVenda());
   * 
   * entity.getItems().add(item);
   * }
   * 
   * entity.setCliente(cliente);
   * copyDtoToEntity(pedidoDTO, entity);
   * itemPedidoRepository.saveAll(entity.getItems());
   * entity = pedidoRepository.save(entity);
   * 
   * return new PedidoDTO(entity);
   * }
   */

  public PedidoDTO update(PedidoDTO pedidoDto, Long id) {

    try {
      Pedido entity = pedidoRepository.findById(id)
          .orElseThrow(() -> new ResourceNotFoundException("Pedido nao econtrado"));
      copyDtoToEntity(pedidoDto, entity);
      entity = pedidoRepository.save(entity);
      return new PedidoDTO(entity);

    } catch (

    EntityNotFoundException e) {
      throw new ResourceNotFoundException("Pedido nao econtrado");

    }

  }

  // @Transactional(propagation = Propagation.SUPPORTS)
  public void deleteById(Long id) {

    try {
      pedidoRepository.deleteById(id);

    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Pedido nao econtrado");
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseExcption("Falha de integridade Referencial");
    }

  }

  private void copyDtoToEntity(PedidoDTO pedidoDto, Pedido entity) {

    entity.setDataEntrega(pedidoDto.getDataEntrega());
    entity.setDataEnvio(pedidoDto.getDataEnvio());
    entity.setDataPedido(Instant.now());
    entity.setValorTotal(pedidoDto.getValorTotal());

  }

}