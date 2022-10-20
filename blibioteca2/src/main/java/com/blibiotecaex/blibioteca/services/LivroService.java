package com.blibiotecaex.blibioteca.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.blibiotecaex.blibioteca.dto.LivroDTO;
import com.blibiotecaex.blibioteca.dto.LivroDTO2;
import com.blibiotecaex.blibioteca.entities.Autor;
import com.blibiotecaex.blibioteca.entities.Categoria;
import com.blibiotecaex.blibioteca.entities.Livro;
import com.blibiotecaex.blibioteca.repository.AutorRepository;
import com.blibiotecaex.blibioteca.repository.CategoriaRepository;
import com.blibiotecaex.blibioteca.repository.LivroRepository;
import com.blibiotecaex.blibioteca.services.exceptions.DatabaseException;
import com.blibiotecaex.blibioteca.services.exceptions.ResourceNotFoundException;

@Service
public class LivroService {

  @Autowired
  LivroRepository livroRepository;
  @Autowired
  AutorRepository autorRepository;
  @Autowired
  CategoriaRepository categoriaRepository;

  public List<LivroDTO2> getAllLivros() {
    List<Livro> listaLivros = livroRepository.findAll();
    List<LivroDTO2> listaLivrosDTO = new ArrayList<>();

    // 1. Percorrer a lista de entidades Editora (chamada listaEditora)
    // 2. Na lista de entidade, pegar cada entidade existente nela
    for (Livro livro : listaLivros) {
      // 3. Transformar cada entidade existente na lista em um DTO
      LivroDTO2 editoraDTO = toDTO(livro);

      // OBS: para converter a entidade no DTO, usar o metodo toDTO
      // 4. Adicionar cada DTO (que foi transformado a partir da entidade) na lista de
      // DTO
      listaLivrosDTO.add(editoraDTO);
    }
    return listaLivrosDTO;
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

  public void deleteById(Long id) {

    try {
      livroRepository.deleteById(id);

    } catch (EmptyResultDataAccessException e) {
      throw new ResourceNotFoundException("Recurso nao encontrado");
    } catch (DataIntegrityViolationException e) {
      throw new DatabaseException("Falha de integridade Referencial");
    }

  }

  private LivroDTO2 toDTO(Livro editora) {
    LivroDTO2 editoraDTO = new LivroDTO2();

    editoraDTO.setAutorid(editora.getAutor().getId());
    editoraDTO.setCategoriaId(editora.getCategoria().getId());
    editoraDTO.setDatapublicacao(editora.getDatapublicacao());
    editoraDTO.setIsbn(editora.getIsbn());
    editoraDTO.setName(editora.getNome());

    return editoraDTO;
  }

}