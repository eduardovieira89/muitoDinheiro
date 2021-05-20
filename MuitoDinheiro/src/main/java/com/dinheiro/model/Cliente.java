package com.dinheiro.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;




@Entity
public class Cliente {
	
	@Size(min = 2)
	@NotEmpty
	@Id
	private String nome;
	
	@OneToMany
	List<Operacao> operacoes;
	
	
	public Cliente(String nome) {
		this.nome = nome;
	}


	public Cliente() {
	}




	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Operacao> getOperacoes() {
		return operacoes;
	}


	public void setOperacoes(List<Operacao> operacoes) {
		this.operacoes = operacoes;
	}

	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Cliente other = (Cliente) obj;
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
