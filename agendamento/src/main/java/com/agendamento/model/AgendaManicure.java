package com.agendamento.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table
public class AgendaManicure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_agenda_manicure")
	private Long id;
	 
	@Column(name = "data_servico")  
	private String data_servico;  
	
	@Column(name = "hora_inicio")
	private String hora_inicio;

	@Column(name = "hora_fim")
	private String hora_fim;

	
	@Column(name = "status_agenda")
	private char status;
	
	@ManyToOne
	@JoinColumn(name="id_manicure")
	private Manicure manicure;
	
	@Transient
	private Long id_manicure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Manicure getManicure() {
		return manicure;
	}

	public void setManicure(Manicure manicure) {
		this.manicure = manicure;
	}

	public Long getId_manicure() {
		return id_manicure;
	}

	public void setId_manicure(Long id_manicure) {
		this.id_manicure = id_manicure;
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
	

}
