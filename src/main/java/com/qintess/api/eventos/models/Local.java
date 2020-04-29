package com.qintess.api.eventos.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="local")
public class Local {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private long idLocal;
	
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;
	
	@Column(name = "endereco", nullable = false, length = 100)
	private String endereco;
	
	@Column(name = "capacidade")
	private int capacidade;
	
	@Column(name = "cep", nullable = false, length = 30)
	private String cep;
	
	@OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
	private List<Evento> eventos = new ArrayList<>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public void setIdLocal(long idLocal) {
		this.idLocal = idLocal;
	}

	public void adicionarEvento(Evento evento) {
		if(eventos == null) {
			eventos =  new ArrayList<>();
		}
		eventos.add(evento);
	}
	
	public long getIdLocal() {
		return idLocal;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
