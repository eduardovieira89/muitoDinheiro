package com.dinheiro.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
public class Operacao {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id_operacao;
	
	@NotNull
	@Valid
	@ManyToOne(cascade=CascadeType.ALL)
	private Cliente cliente;
	
	
	@ManyToOne
	private TaxaCambio taxaCambio;
	
	private LocalDate dataOperacao;
	
	@NotNull(message = "O valor não pode ser nulo")
	private double valorOriginal;
	private double valorConvertido;
	private double taxaCobrada;
	
	
	
	
	public long getId_operacao() {
		return id_operacao;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public TaxaCambio getTaxaCambio() {
		return taxaCambio;
	}
	public void setTaxaCambio(TaxaCambio taxaCambio) {
		this.taxaCambio = taxaCambio;
	}
	public LocalDate getDataOperacao() {
		return dataOperacao;
	}
	public void setDataOperacao(LocalDate dataOperacao) {
		this.dataOperacao = dataOperacao;
	}
	public double getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public double getValorConvertido() {
		return valorConvertido;
	}
	public void setValorConvertido(double valorConvertido) {
		this.valorConvertido = valorConvertido;
	}
	public double getTaxaCobrada() {
		return taxaCobrada;
	}
	public void setTaxaCobrada(double taxaCobrada) {
		this.taxaCobrada = taxaCobrada;
	}
	
	
	public Operacao(Cliente cliente, TaxaCambio taxaCambio, LocalDate dataOperacao, double valorOriginal,
			double valorConvertido, double taxaCobrada) {
		this.cliente = cliente;
		this.taxaCambio = taxaCambio;
		this.dataOperacao = dataOperacao;
		this.valorOriginal = valorOriginal;
		this.valorConvertido = valorConvertido;
		this.taxaCobrada = taxaCobrada;
	}
	public Operacao() {
	}
	
	
	
	
	
	
	
	
	

}
