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

@Entity
@Table
public class AgendaManicure {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_agenda_manicure")
	private Long id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_inicio",columnDefinition="DATE")
	private Date data_inicio;
	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_fim",columnDefinition="DATE")
	private Date data_fim;
	
	@Column(name = "status_agenda")
	private char status;
	
	@ManyToOne
	@JoinColumn(name="id_manicure")
	private Manicure manicure;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData_inicio() {
		return data_inicio;
	}

	public void setData_inicio(Date data_inicio) {
		this.data_inicio = data_inicio;
	}

	public Date getData_fim() {
		return data_fim;
	}

	public void setData_fim(Date data_fim) {
		this.data_fim = data_fim;
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
	

}
