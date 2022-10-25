package com.serratec.ecommerce.ecommerce.dto;

import java.sql.Date;

import com.serratec.ecommerce.ecommerce.model.Produto;

public class ProdutoDTO {
	private Long id;

	private String nome;
	private Integer qtdEstoque;
	private Date dataCadastro;
	private Double valorUnitario;
	private String descricao;
	private String imagemUrl;
	private CategoriaDTO categoria;

	public ProdutoDTO() {
	}

	public ProdutoDTO(Produto entity) {
		id = entity.getId();
		nome = entity.getNome();
		qtdEstoque = entity.getQtdEstoque();
		dataCadastro = entity.getDataCadastro();
		valorUnitario = entity.getValorUnitario();
		imagemUrl = entity.getImagemUrl();
		categoria = new CategoriaDTO(entity.getCategoria());
		descricao = entity.getDescricao();

	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Double getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public String getImagemUrl() {
		return imagemUrl;
	}

	public void setImagemUrl(String imagemUrl) {
		this.imagemUrl = imagemUrl;
	}

	public CategoriaDTO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaDTO categoria) {
		this.categoria = categoria;
	}

}