package com.agendamento.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cliente")
	private Long id;
	
	@Column(name = "nome_cliente")
	private String nome;
	
	@Column(name = "id_facebook")
	private String id_facebook;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cliente")
	private List<Atendimento> atendimentos = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getId_facebook() {
		return id_facebook;
	}

	public void setId_facebook(String id_facebook) {
		this.id_facebook = id_facebook;
	}

	public List<Atendimento> getAtendimentos() {
		return atendimentos;
	}

	public void setAtendimentos(List<Atendimento> atendimentos) {
		this.atendimentos = atendimentos;
	}
	
	
}
