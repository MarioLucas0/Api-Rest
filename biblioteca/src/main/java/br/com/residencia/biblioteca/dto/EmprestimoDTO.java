package br.com.residencia.biblioteca.dto;

import java.math.BigDecimal;
import java.time.Instant;

import br.com.residencia.biblioteca.entity.Aluno;
import br.com.residencia.biblioteca.entity.Livro;

public class EmprestimoDTO {
	private Integer codigoEmprestimo;

	private Instant dataEmprestimo;

	private Instant dataEntrega;

	private BigDecimal valorEmprestimo;

	private Aluno aluno;

	private Livro livro;

//	Getters and Setters
	public Instant getDataEmprestimo() {
		return dataEmprestimo;
	}

	public Integer getCodigoEmprestimo() {
		return codigoEmprestimo;
	}

	public void setCodigoEmprestimo(Integer codigoEmprestimo) {
		this.codigoEmprestimo = codigoEmprestimo;
	}

	public void setDataEmprestimo(Instant dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Instant getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Instant dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public BigDecimal getValorEmprestimo() {
		return valorEmprestimo;
	}

	public void setValorEmprestimo(BigDecimal valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}
}