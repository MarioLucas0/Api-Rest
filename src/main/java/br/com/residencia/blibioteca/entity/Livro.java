package br.com.residencia.blibioteca.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "livros")
public class Livro {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "codigolivro")
  private Integer codigoLivro;

  @Column(name = "nomelivro")
  private String nomeLivro;

  @Column(name = "nomeautorizado")
  private String nomeAutor;

  @Column(name = "datalancamento")
  private Date dataLancamento;

  @Column(name = "codigoisbn")
  private Integer codigoIsbn;

  @ManyToOne
  @JoinColumn(name = "codigoeditora", referencedColumnName = "codigoeditora")
  private Editora editora;

  @OneToOne(mappedBy = "livro")
  private Emprestimo emprestimo;

  public Integer getCodigoLivro() {
    return this.codigoLivro;
  }

  public void setCodigoLivro(Integer codigoLivro) {
    this.codigoLivro = codigoLivro;
  }

  public String getNomeLivro() {
    return this.nomeLivro;
  }

  public void setNomeLivro(String nomeLivro) {
    this.nomeLivro = nomeLivro;
  }

  public String getNomeAutor() {
    return this.nomeAutor;
  }

  public void setNomeAutor(String nomeAutor) {
    this.nomeAutor = nomeAutor;
  }

  public Date getDataLancamento() {
    return this.dataLancamento;
  }

  public void setDataLancamento(Date dataLancamento) {
    this.dataLancamento = dataLancamento;
  }

  public Integer getCodigoIsbn() {
    return this.codigoIsbn;
  }

  public void setCodigoIsbn(Integer codigoIsbn) {
    this.codigoIsbn = codigoIsbn;
  }

  public Editora getEditora() {
    return this.editora;
  }

  public void setEditora(Editora editora) {
    this.editora = editora;
  }

  public Emprestimo getEmprestimo() {
    return this.emprestimo;
  }

  public void setEmprestimo(Emprestimo emprestimo) {
    this.emprestimo = emprestimo;
  }

}
