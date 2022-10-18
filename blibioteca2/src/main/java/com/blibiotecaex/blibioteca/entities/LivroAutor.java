package com.blibiotecaex.blibioteca.entities;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_livroautor")
public class LivroAutor {
  @EmbeddedId
  private LivroAutorPK id = new LivroAutorPK();

  public LivroAutor() {
  }

  public LivroAutor(Livro livro, Autor autor) {
    id.setLivro(livro);
    id.setAutor(autor);

  }

  public Livro getLivros() {
    return id.getLivro();
  }

  public void setLivros(Livro livro) {
    id.setLivro(livro);
  }

  public void setAutor(Autor autor) {
    id.setAutor(autor);
  }

  public Autor getAutor() {
    return id.getAutor();
  }

}