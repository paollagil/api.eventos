package com.qintess.api.eventos.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="evento")
public class Evento {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
	private long idEvento;
	
	@Column(name = "nome", nullable = false, length = 80)
	private String nome;
	
	@Column(name = "data_hora", nullable = false)
	private Date dataHora;
	
	@Column(name = "qt_ingresso", nullable = false)
	private int quantidadeIngresso;
	
	@Column(name = "indicador", nullable = false)
	private int indicador;
	
	@Column(name = "valor", nullable = false)
	private double valorIngresso;
	
	@Column(name = "data_limite", nullable = false)
	private Date dataLimite;
	
	@Column(name = "descricao", nullable = false, length = 100)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "local_id", nullable = false)
    private Local local;
	
	@OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
	private List<Ingresso> ingresso = new ArrayList<>();

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public String getNome() {
		return nome;
	}

	public long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(long idEvento) {
		this.idEvento = idEvento;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public Date getDataHora() {
		return dataHora;
	}


	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}


	public int getQuantidadeIngresso() {
		return quantidadeIngresso;
	}


	public void setQuantidadeIngresso(int quantidadeIngresso) {
		this.quantidadeIngresso = quantidadeIngresso;
	}


	public int getIndicador() {
		return indicador;
	}


	public void setIndicador(int indicador) {
		this.indicador = indicador;
	}


	public double getValorIngresso() {
		return valorIngresso;
	}


	public void setValorIngresso(double valorIngresso) {
		this.valorIngresso = valorIngresso;
	}


	public Date getDataLimite() {
		return dataLimite;
	}


	public void setDataLimite(Date dataLimite) {
		this.dataLimite = dataLimite;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
