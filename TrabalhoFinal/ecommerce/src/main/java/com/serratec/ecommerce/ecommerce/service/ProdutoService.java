package com.serratec.ecommerce.ecommerce.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.serratec.ecommerce.ecommerce.dto.ProdutoDTO;
import com.serratec.ecommerce.ecommerce.model.Produto;
import com.serratec.ecommerce.ecommerce.repository.ProdutoRepository;
import com.serratec.ecommerce.ecommerce.service.exceptions.DatabaseExcption;
import com.serratec.ecommerce.ecommerce.service.exceptions.ResourceNotFoundException;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository produtoRepository;

  public ProdutoDTO findById(Long id) {

    Produto produto = produtoRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
    ProdutoDTO dto = new ProdutoDTO(produto);
    return dto;

  }

  public List<ProdutoDTO> findAll() {
    List<Produto> result = produtoRepository.findAll();
    return result.stream().map(x -> new ProdutoDTO(x)).toList();
  }

  @Transactional
  public ProdutoDTO insert(ProdutoDTO productDto) {

    Produto entity = new Produto();
    copyDtoToEntity(productDto, entity);
    entity = produtoRepository.save(entity);
    return new ProdutoDTO(entity);
  }

  public ProdutoDTO update(ProdutoDTO productDto, Long id) {

    try {
      Produto entity = new Produto();
      copyDtoToEntity(productDto, entity);
      entity = produtoRepository.save(entity);
      return new ProdutoDTO(entity);

    } catch (

    EntityNotFoundException e) {
      throw new ResourceNotFoundException("Recurso nao encontrado");

    }

  }

  // @Transactional(propagation = Propagation.SUPPORTS)
  public void deleteById(Long id) {

    try {
      produtoRepository.deleteById(id);

    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Recurso nao encontrado");
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseExcption("Falha de integridade Referencial");
    }

  }

  private void copyDtoToEntity(ProdutoDTO productDto, Produto entity) {

    entity.setNome(productDto.getNome());
    entity.setDataCadastro(productDto.getDataCadastro());
    entity.setQtdEstoque(productDto.getQtdEstoque());
    entity.setValorUnitario(productDto.getValorUnitario());
  }

}