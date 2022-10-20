package br.com.residencia.biblioteca.dto;

import java.util.List;

public class EditoraDTO {
	private Integer codigoEditora;
	private String nome;
	private List<LivroDTO> listaLivrosDTO;

	public EditoraDTO() {
	}

	public EditoraDTO(Integer codigoEditora, String nome) {
		this.codigoEditora = codigoEditora;
		this.nome = nome;
	}

//	Getters and Setters 
	public Integer getCodigoEditora() {
		return codigoEditora;
	}

	public List<LivroDTO> getListaLivrosDTO() {
		return listaLivrosDTO;
	}

	public void setListaLivrosDTO(List<LivroDTO> listaLivrosDTO) {
		this.listaLivrosDTO = listaLivrosDTO;
	}

	public void setCodigoEditora(Integer codigoEditora) {
		this.codigoEditora = codigoEditora;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
