package br.com.residencia.blibioteca.entity;

import java.sql.Date;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "alunos")
public class Alunos {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "numeromatriculaaluno")
	@OneToOne(mappedBy = "numeromatriculaaluno")
	private Integer numeroMatriculaAluno;
	@Column(name = "nome")
	private String nome;
	@Column(name = "datanascimento")
	private Date datanascimento;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "numerologradouro")
	private String numeroLogradouro;
	@Column(name = "complemento")
	private String Complemento;
	@Column(name = "bairro")
	private String bairro;
	@Column(name = "cidade")

	@OneToMany(mappedBy = "aluno")
	private Set<Emprestimo> emprestimos;

	public Integer getNumeroMatriculaAluno() {
		return this.numeroMatriculaAluno;
	}

	public void setNumeroMatriculaAluno(Integer numeroMatriculaAluno) {
		this.numeroMatriculaAluno = numeroMatriculaAluno;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDatanascimento() {
		return this.datanascimento;
	}

	public void setDatanascimento(Date datanascimento) {
		this.datanascimento = datanascimento;
	}

	public String getCpf() {
		return this.cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNumeroLogradouro() {
		return this.numeroLogradouro;
	}

	public void setNumeroLogradouro(String numeroLogradouro) {
		this.numeroLogradouro = numeroLogradouro;
	}

	public String getComplemento() {
		return this.Complemento;
	}

	public void setComplemento(String Complemento) {
		this.Complemento = Complemento;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Set<Emprestimo> getEmprestimos() {
		return this.emprestimos;
	}

	public void setEmprestimos(Set<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

}
