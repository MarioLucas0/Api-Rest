package br.com.residencia.cinema.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "diretor")
public class Diretor {

	@Id
	@Column(name = "iddiretor", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDiretor;

	@Column(name = "nomediretor", length = 100)
	private String nomeDiretor;

	// @JsonManagedReference
	@JsonIgnore
	@OneToMany(mappedBy = "diretor")
	private List<Filme> filmes;

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Integer getIdDiretor() {
		return idDiretor;
	}

	public void setIdDiretor(Integer idDiretor) {
		this.idDiretor = idDiretor;
	}

	public String getNomeDiretor() {
		return nomeDiretor;
	}

	public void setNomeDiretor(String nomeDiretor) {
		this.nomeDiretor = nomeDiretor;
	}

}
