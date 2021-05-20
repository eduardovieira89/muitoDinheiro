package com.dinheiro.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dinheiro.model.Cliente;
import com.dinheiro.model.Operacao;

public interface OperacaoRepository extends JpaRepository<Operacao, Long> {
	
	List<Operacao> findByCliente(Cliente cliente);
	@Query("SELECT SUM(o.valorConvertido) FROM Operacao o WHERE o.cliente=?1")
	Double somaValorConvertidoByCliente(Cliente cliente);
	@Query("SELECT SUM(o.taxaCobrada) FROM Operacao o WHERE o.cliente=?1")
	Double somaTaxaCobradaByCliente(Cliente cliente);
	
	List<Operacao> findByDataOperacao (LocalDate data);
	@Query("SELECT SUM(o.valorConvertido) FROM Operacao o WHERE o.dataOperacao=?1")
	Double somaValorConvertidoByDataOperacao(LocalDate data);
	@Query("SELECT SUM(o.taxaCobrada) FROM Operacao o WHERE o.dataOperacao=?1")
	Double somaTaxaCobradabyDataOperacao(LocalDate data);
	
	List<Operacao> findByDataOperacaoBetween (LocalDate dtInicio, LocalDate dtFim);
	@Query("SELECT SUM(o.valorConvertido) FROM Operacao o WHERE o.dataOperacao BETWEEN ?1 AND ?2")
	Double somaValorConvertidoByDataOperacaoBetween (LocalDate dtInicio, LocalDate dtFim);
	@Query("SELECT SUM(o.taxaCobrada) FROM Operacao o WHERE o.dataOperacao BETWEEN ?1 AND ?2")
	Double somaTaxaCobradaByDataOperacaoBetween (LocalDate dtInicio, LocalDate dtFim);
	
	List<Operacao> findByClienteAndDataOperacao (Cliente cliente, LocalDate data);
	@Query("SELECT SUM(o.valorConvertido) FROM Operacao o WHERE o.cliente=?1 AND o.dataOperacao=?2")
	Double somaValoConvertidoByClienteAndDataOperacao (Cliente cliente, LocalDate data);
	@Query("SELECT SUM(o.taxaCobrada) FROM Operacao o WHERE o.cliente=?1 AND o.dataOperacao=?2")
	Double somaTaxaCobradaByClienteAndDataOperacao (Cliente cliente, LocalDate data);
	
	List<Operacao> findByClienteAndDataOperacaoBetween (Cliente cliente, LocalDate dtInicio, LocalDate dtFim);
	@Query("SELECT SUM(o.valorConvertido) FROM Operacao o WHERE o.cliente=?1 AND o.dataOperacao BETWEEN ?2 AND ?3")
	Double somaValorConvertidoByClienteAndDataOperacaoBetween (Cliente cliente, LocalDate dtInicio, LocalDate dtFim);
	@Query("SELECT SUM(o.taxaCobrada) FROM Operacao o WHERE o.cliente=?1 AND o.dataOperacao BETWEEN ?2 AND ?3")
	Double somaTaxaCobradaByClienteAndDataOperacaoBetween (Cliente cliente, LocalDate dtInicio, LocalDate dtFim);
	
	
	
	

}
