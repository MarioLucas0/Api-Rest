package com.blibiotecaex.blibioteca.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.AutorDTO;
import com.blibiotecaex.blibioteca.entities.Autor;
import com.blibiotecaex.blibioteca.repository.AutorRepository;

@Service
public class AutorService {

  @Autowired
  AutorRepository autorRepository;

  @Transactional
  public List<Autor> getAllAutor() {
    return autorRepository.findAll();

  }

  public Autor getLivroById(Long id) {
    return autorRepository.findById(id).get();
  }

  @Transactional
  public AutorDTO insert(AutorDTO productDto) {

    Autor entity = new Autor();

    entity.setId(productDto.getId());
    entity.setNome(productDto.getName());

    entity = autorRepository.save(entity);

    return new AutorDTO(entity);
  }

  /*
   * private void copyDtoToEntity(AutorDTO autorDTO, Autor entity) {
   * 
   * entity.setNome(autorDTO.getName());
   * }
   */

}