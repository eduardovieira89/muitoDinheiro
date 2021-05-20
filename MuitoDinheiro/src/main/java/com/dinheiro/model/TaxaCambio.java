package com.dinheiro.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="taxa_cambio")
public class TaxaCambio {
	

	@EmbeddedId
	private MoedaOrigemDestino origemDestino;
	
	private double taxa;
	

	public double getTaxa() {
		return taxa;
	}
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}
	public MoedaOrigemDestino getOrigemDestino() {
		return origemDestino;
	}
	public void setOrigemDestino(MoedaOrigemDestino origemDestino) {
		this.origemDestino = origemDestino;
	}
	
	
	public TaxaCambio(MoedaOrigemDestino origemDestino, double taxa) {
		this.origemDestino = origemDestino;
		this.taxa = taxa;
	}
	public TaxaCambio() {
	}
	@Override
	public String toString() {
		return "TaxaCambio [taxa=" + taxa + "]";
	}
	
	
	
	
	

}
