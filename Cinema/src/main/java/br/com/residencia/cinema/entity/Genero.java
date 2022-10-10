package br.com.residencia.cinema.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Genero")
public class Genero {

	@Id
	@Column(name = "idgenero")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idGenero;
	
	@Column(name = "descricao",length = 100)
	private String descricao;
	
	//@JsonBackReference
	@JsonIgnore
	@OneToMany(mappedBy = "genero")
	private List<Filme> filmes;	
	
	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

	public Integer getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
