package com.dinheiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dinheiro.model.Moeda;
import com.dinheiro.model.MoedaOrigemDestino;
import com.dinheiro.model.TaxaCambio;

public interface TaxaCambioRepository extends JpaRepository<TaxaCambio, MoedaOrigemDestino> {
	TaxaCambio findByorigemDestino(MoedaOrigemDestino moedas);
	
	@Query("SELECT t.origemDestino.destino FROM TaxaCambio t WHERE t.origemDestino.origem = ?1")
	List<Moeda> buscaMoedaDestinoByMoedaOrigem(Moeda origem);

}
