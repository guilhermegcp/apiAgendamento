package com.agendamento.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table
public class Atendimento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_atendimento")
	private Long id;
	
	@Column(name = "data_servico")  
	private String data_servico;  
	
	@Column(name = "hora_inicio")
	private String hora_inicio;

	@Column(name = "hora_fim")
	private String hora_fim;
	
	@Column(name = "status_atendimento")
	private char status;
	
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;

	@ManyToOne
	@JoinColumn(name="id_manicure")
	private Manicure manicure;
	
	@ManyToMany
	@JoinTable(
    name = "servico_atendimento", 
	joinColumns = @JoinColumn(name = "id_atendimento"), 
	inverseJoinColumns = @JoinColumn(name = "id_servico"))
	List<Servico> servico;
 
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Manicure getManicure() {
		return manicure;
	}

	public void setManicure(Manicure manicure) {
		this.manicure = manicure;
	}

	public List<Servico> getServico() {
		return servico;
	}

	public void setServico(List<Servico> servico) {
		this.servico = servico;
	}
	
	
}
