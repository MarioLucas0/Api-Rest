package com.blibiotecaex.blibioteca.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.CategoriaDTO;
import com.blibiotecaex.blibioteca.entities.Categoria;
import com.blibiotecaex.blibioteca.repository.CategoriaRepository;

@Service
public class CategoriaService {

  @Autowired
  CategoriaRepository categoriaRepository;

  @Transactional
  public List<Categoria> getAllCategorias() {
    return categoriaRepository.findAll();
  }

  public Categoria getCategoriaById(Long id) {
    return categoriaRepository.findById(id).get();

  }

  @Transactional
  public CategoriaDTO insert(CategoriaDTO categoriaDto) {

    Categoria entity = new Categoria();

    entity.setId(categoriaDto.getId());
    entity.setNome(categoriaDto.getNome());

    entity = categoriaRepository.save(entity);

    return new CategoriaDTO(entity);
  }

}