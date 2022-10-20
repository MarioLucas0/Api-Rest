package br.com.residencia.biblioteca.dto;

import java.util.ArrayList;
import java.util.List;

public class AlunoEmprestimoDTO {
	private Integer numeroMatriculaAluno;
	private String nome;
	private String cpf;
	private List<ResumoEmprestimoDTO> resumoEmprestimoDTO = new ArrayList<>();


	//	Getters and Setters
	public String getNome() {
		return nome;
	}

	public List<ResumoEmprestimoDTO> getResumoEmprestimoDTO() {
		return resumoEmprestimoDTO;
	}

	public void setResumoEmprestimoDTO(List<ResumoEmprestimoDTO> resumoEmprestimoDTO) {
		this.resumoEmprestimoDTO = resumoEmprestimoDTO;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
}