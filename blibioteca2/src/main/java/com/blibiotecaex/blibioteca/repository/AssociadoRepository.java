package com.blibiotecaex.blibioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blibiotecaex.blibioteca.entities.Associado;

@Repository
public interface AssociadoRepository extends JpaRepository<Associado, Long> {

    // Usuario findByEmail(String email);

    // Optional<Usuario> findByNome(String nome);
}
