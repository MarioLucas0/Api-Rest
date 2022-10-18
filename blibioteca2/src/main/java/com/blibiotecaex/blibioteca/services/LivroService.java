package com.blibiotecaex.blibioteca.services;

import java.util.List;

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
  /*
   * public Livro updateLivro(Livro livro, Long id) {
   * Livro livroExistenteNoBanco = livroRepository.findById(id).get();
   * 
   * livroExistenteNoBanco.setNome(livro.getNome());
   * livroExistenteNoBanco.setDatapublicacao(livro.getDatapublicacao());
   * livroExistenteNoBanco.setIsbn(livro.getIsbn());
   * livroExistenteNoBanco.setCategoria(livro.getCategoria());
   * 
   * return livroRepository.save(livroExistenteNoBanco);
   * 
   * }
   */

  /*
   * public Livro deleteLivro(Long id) {
   * livroRepository.deleteById(id);
   * return getLivroById(id);
   * }
   */

}