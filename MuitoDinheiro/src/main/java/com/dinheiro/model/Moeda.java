package com.dinheiro.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="moeda")
public class Moeda {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private long id_moeda;
	@NotEmpty
	private String nome;
	private String pais;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public long getId_moeda() {
		return id_moeda;
	}
	
	public Moeda(String nome, String pais) {
		this.nome = nome;
		this.pais = pais;
	}
	
	public Moeda(String nome) {
		this.nome = nome;
	}
	
	public Moeda() {
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id_moeda ^ (id_moeda >>> 32));
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Moeda other = (Moeda) obj;
		if (id_moeda != other.id_moeda)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return this.nome;
	}
	
	
	
	
	
	
	

}
