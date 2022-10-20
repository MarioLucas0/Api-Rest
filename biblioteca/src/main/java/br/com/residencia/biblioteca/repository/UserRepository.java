package br.com.residencia.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.residencia.biblioteca.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
	Optional<User> findByUserEmail(String email);
}