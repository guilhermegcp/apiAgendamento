package com.agendamento.dto;

import java.io.Serializable;

import javax.persistence.Column;

public class AgendaManicureDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long 	id_manicure;
	private String nome_manicure;
	private char status;
	private String data_servico;  
	private String hora_inicio;
	private String hora_fim;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId_manicure() {
		return id_manicure;
	}
	public void setId_manicure(Long id_manicure) {
		this.id_manicure = id_manicure;
	}
	public String getNome_manicure() {
		return nome_manicure;
	}
	public void setNome_manicure(String nome_manicure) {
		this.nome_manicure = nome_manicure;
	}

	public char getStatus() {
		return status;
	}
	public void setStatus(char status) {
		this.status = status;
	}
	
	public String getData_servico() {
		return data_servico;
	}
	public void setData_servico(String data_servico) {
		this.data_servico = data_servico;
	}
	public String getHora_inicio() {
		return hora_inicio;
	}
	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}
	public String getHora_fim() {
		return hora_fim;
	}
	public void setHora_fim(String hora_fim) {
		this.hora_fim = hora_fim;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
