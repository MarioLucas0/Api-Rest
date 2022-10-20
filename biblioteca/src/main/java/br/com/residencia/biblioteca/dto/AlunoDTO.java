package br.com.residencia.biblioteca.dto;

import java.time.Instant;
import java.util.List;

public class AlunoDTO {
	private Integer numeroMatriculaAluno;
	
	private String nome;

	private Instant dataNascimento;

	private String cpf;

	private String logradouro;

	private String numeroLogradouro;

	private String complemento;

	private String bairro;

	private String cidade;
	
	private List<EmprestimoDTO> emprestimos;

//	Getters and Setters
	public String getNome() {
		return nome;
	}

	public List<EmprestimoDTO> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<EmprestimoDTO> emprestimos) {
		this.emprestimos = emprestimos;
	}

	public Integer getNumeroMatriculaAluno() {
		return numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Instant getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Instant dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumeroLogradouro() {
		return numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
}