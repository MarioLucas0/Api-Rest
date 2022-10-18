package com.blibiotecaex.blibioteca.entities;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_emprestimo")
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant dataemprestimo;

    @ManyToOne
    @JoinColumn(name = "associado_id")
    private Associado associado;

    public Emprestimo() {
    }

    public Emprestimo(Long id, Instant dataemprestimo, Associado associado) {
        this.id = id;
        this.dataemprestimo = dataemprestimo;
        this.associado = associado;
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

    public Associado getAssociado() {
        return this.associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

}
