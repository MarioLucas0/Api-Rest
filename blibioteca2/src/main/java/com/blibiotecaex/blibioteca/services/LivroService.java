package com.blibiotecaex.blibioteca.services;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.LivroDTO;
import com.blibiotecaex.blibioteca.dto.LivroDTO2;
import com.blibiotecaex.blibioteca.entities.Autor;
import com.blibiotecaex.blibioteca.entities.Categoria;
import com.blibiotecaex.blibioteca.entities.Livro;
import com.blibiotecaex.blibioteca.repository.AutorRepository;
import com.blibiotecaex.blibioteca.repository.CategoriaRepository;
import com.blibiotecaex.blibioteca.repository.LivroRepository;
import com.blibiotecaex.blibioteca.services.exceptions.ResourceNotFoundException;

@Service
public class LivroService {

  @Autowired
  LivroRepository livroRepository;
  @Autowired
  AutorRepository autorRepository;
  @Autowired
  CategoriaRepository categoriaRepository;

  @Transactional
  public List<Livro> findAll() {
    return livroRepository.findAll();

  }

  public Livro getLivroById(Long id) {
    return livroRepository.findById(id).get();
  }

  public Livro saveLivro(@Valid LivroDTO li) {
    return livroRepository.save(li);
  }

  /***********************************
   * Post aninhado
   **********************************/
  /*
   * @Transactional
   * public LivroDTO insert(LivroDTO livroDto) {
   * 
   * Livro entity = new Livro();
   * 
   * entity.setNome(livroDto.getName());
   * entity.setDatapublicacao(livroDto.getDatapublicacao());
   * entity.setIsbn(livroDto.getIsbn());
   * 
   * Autor autor =
   * autorRepository.getReferenceById(livroDto.getAutorDto().getId());
   * // Autor autor = new Autor();
   * autor.setId(livroDto.getAutorDto().getId());
   * // autor.setNome(livroDto.getAutorDto().getName());
   * 
   * entity.setAutor(autor);
   * entity = livroRepository.save(entity);
   * 
   * return new LivroDTO(entity);
   * }
   */
  @Transactional
  public LivroDTO2 insert(LivroDTO2 livroDto) {

    Livro entity = new Livro();

    entity.setNome(livroDto.getName());
    entity.setDatapublicacao(livroDto.getDatapublicacao());
    entity.setIsbn(livroDto.getIsbn());

    Autor autor = autorRepository.getReferenceById(livroDto.getAutorid());
    Categoria categoria = categoriaRepository.getReferenceById(livroDto.getCategoriaId());
    // Autor autor = new Autor();
    // autor.setId(livroDto.getAutorid());

    entity.setCategoria(categoria);
    entity.setAutor(autor);
    entity = livroRepository.save(entity);

    return new LivroDTO2(entity);
  }

  @Transactional
  public LivroDTO2 update(LivroDTO2 livroDto, Long id) {

    try {
      Livro entity = livroRepository.getReferenceById(id);
      copyDtoToEntity(livroDto, entity);
      entity = livroRepository.save(entity);
      return new LivroDTO2(entity);

    } catch (EntityNotFoundException e) {
      throw new ResourceNotFoundException("Recurso nao encontrado");
    }

  }

  private void copyDtoToEntity(LivroDTO2 livroDto, Livro entity) {

    entity.setNome(livroDto.getName());
    entity.setIsbn(livroDto.getIsbn());
    entity.setDatapublicacao(livroDto.getDatapublicacao());

    Autor autor = autorRepository.getReferenceById(livroDto.getAutorid());
    Categoria categoria = categoriaRepository.getReferenceById(livroDto.getCategoriaId());

    entity.setAutor(autor);
    entity.setCategoria(categoria);
  }

  /*
   * public Livro deleteLivro(Long id) {
   * livroRepository.deleteById(id);
   * return getLivroById(id);
   * }
   */

}