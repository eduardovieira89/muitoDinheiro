package com.dinheiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dinheiro.model.Moeda;

public interface MoedaRepository extends JpaRepository<Moeda, Long> {

}
