package com.blibiotecaex.blibioteca.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class LivroAutorPK implements Serializable {
  @ManyToOne
  @JoinColumn(name = "livro_id")
  private Livro livro;
  @ManyToOne
  @JoinColumn(name = "autor_id")
  private Autor autor;

  public Livro getLivro() {
    return this.livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  public Autor getAutor() {
    return this.autor;
  }

  public void setAutor(Autor autor) {
    this.autor = autor;
  }

}