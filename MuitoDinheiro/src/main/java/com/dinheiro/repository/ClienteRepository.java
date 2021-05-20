package com.dinheiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinheiro.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String> {
	Cliente findByNome(String nome);

}
