package com.dinheiro.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


@Embeddable
public class MoedaOrigemDestino implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	@ManyToOne
	private Moeda origem;
	
	@ManyToOne
	private Moeda destino;
	
	public MoedaOrigemDestino() {}
	
	public MoedaOrigemDestino(Moeda origem, Moeda destino) {
		this.origem = origem;
		this.destino = destino;
	}

	public Moeda getOrigem() {
		return origem;
	}

	public void setOrigem(Moeda origem) {
		this.origem = origem;
	}

	public Moeda getDestino() {
		return destino;
	}

	public void setDestino(Moeda destino) {
		this.destino = destino;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((destino == null) ? 0 : destino.hashCode());
		result = prime * result + ((origem == null) ? 0 : origem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MoedaOrigemDestino other = (MoedaOrigemDestino) obj;
		if (destino == null) {
			if (other.destino != null)
				return false;
		} else if (!destino.equals(other.destino))
			return false;
		if (origem == null) {
			if (other.origem != null)
				return false;
		} else if (!origem.equals(other.origem))
			return false;
		return true;
	}
	
	
	
}
