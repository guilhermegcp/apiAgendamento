package com.agendamento.modelMesagesQuick;

public class Atributos {
	
	private String codManicure;
	private String nomeManicure;
	private String codAgenda;
	private String codServico;
	
	public Atributos() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Atributos(String codManicure, String nomeManicure, String codAgenda, String codServico) {
		super();
		this.codManicure = codManicure;
		this.nomeManicure = nomeManicure;
		this.codAgenda = codAgenda;
		this.codServico = codServico;
	}

	public String getCodManicure() {
		return codManicure;
	}

	public void setCodManicure(String codManicure) {
		this.codManicure = codManicure;
	}

	public String getNomeManicure() {
		return nomeManicure;
	}

	public void setNomeManicure(String nomeManicure) {
		this.nomeManicure = nomeManicure;
	}

	public String getCodAgenda() {
		return codAgenda;
	}

	public void setCodAgenda(String codAgenda) {
		this.codAgenda = codAgenda;
	}

	public String getCodServico() {
		return codServico;
	}

	public void setCodServico(String codServico) {
		this.codServico = codServico;
	}
	
	
}
