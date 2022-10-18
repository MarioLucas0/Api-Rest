package com.blibiotecaex.blibioteca.dto;

import java.time.Instant;

import com.blibiotecaex.blibioteca.entities.Emprestimo;

public class EmprestimoDTO {

  private Long id;
  private Instant dataemprestimo;
  private Long associadoId;

  public EmprestimoDTO(Long id, Instant dataemprestimo, Long associado) {
    this.id = id;
    this.dataemprestimo = dataemprestimo;
    this.associadoId = associado;
  }

  public EmprestimoDTO(Emprestimo emprestimo) {
    this.id = emprestimo.getId();
    this.dataemprestimo = emprestimo.getDataemprestimo();
    this.associadoId = emprestimo.getAssociado().getId();
  }

  public Long getId() {
    return this.id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Instant getDataemprestimo() {
    return this.dataemprestimo;
  }

  public void setDataemprestimo(Instant dataemprestimo) {
    this.dataemprestimo = dataemprestimo;
  }

  public Long getAssociadoId() {
    return this.associadoId;
  }

  public void setAssociadoId(Long associadoId) {
    this.associadoId = associadoId;
  }
}
