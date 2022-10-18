package com.blibiotecaex.blibioteca.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_livro")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant datapublicacao;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    public Livro() {
    }

    public Livro(Long id, String nome, Instant datapublicacao, String isbn, Autor autor, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.datapublicacao = datapublicacao;
        this.isbn = isbn;
        this.autor = autor;
        this.categoria = categoria;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Instant getDatapublicacao() {
        return this.datapublicacao;
    }

    public void setDatapublicacao(Instant datapublicacao) {
        this.datapublicacao = datapublicacao;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /*
     * public List<Livro> getLivros() {
     * return this.livros;
     * }
     * 
     * public void setLivros(List<Livro> livros) {
     * this.livros = livros;
     * }
     */
    public Autor getAutor() {
        return this.autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
