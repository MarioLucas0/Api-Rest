package com.serratec.ecommerce.ecommerce.dto;

import java.time.LocalDate;

import com.serratec.ecommerce.ecommerce.model.Cliente;

public class ClienteListDTO {

    private Long id;
    private String email;
    private String nomeCompleto;
    private String cpf;
    private LocalDate dataNascimento;
    private EnderecoDTO endereco;
    // private Set<Pedido> pedidos;

    public ClienteListDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.email = cliente.getEmail();
        this.nomeCompleto = cliente.getNomeCompleto();
        this.cpf = cliente.getCpf();
        this.endereco = new EnderecoDTO(cliente.getEndereco());
        this.dataNascimento = cliente.getDataNascimento();
        // this.pedidos = cliente.getPedidos();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return this.nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return this.dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public EnderecoDTO getEndereco() {
        return this.endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    /*
     * public Set<Pedido> getPedidos() {
     * return this.pedidos;
     * }
     * 
     * public void setPedidos(Set<Pedido> pedidos) {
     * this.pedidos = pedidos;
     * }
     */

}
