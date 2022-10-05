package br.com.residencia.blibioteca.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "editora")
public class Editora {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "codigoeditora")
	private Integer codigoEditora;
	@Column(name = "nome")
	private String nome;

	@OneToMany(mappedBy = "codigoEditora")
	private Set<Livro> livros;

	public Integer getCodigoEditora() {
		return this.codigoEditora;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Livro> getLivros() {
		return this.livros;
	}

	public void setLivros(Set<Livro> livros) {
		this.livros = livros;
	}

}
