package com.agendamento.dto;

import java.io.Serializable;

public class AtendimentoDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private Long id_manicure;
	private Long id_cliente;
	private Long id_servico;
	private Long id_agenda_manicure;
	private char status_atendimento;
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
	public Long getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Long id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Long getId_servico() {
		return id_servico;
	}
	public void setId_servico(Long id_servico) {
		this.id_servico = id_servico;
	}
	public char getStatus_atendimento() {
		return status_atendimento;
	}
	public void setStatus_atendimento(char status_atendimento) {
		this.status_atendimento = status_atendimento;
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
	public Long getId_agenda_manicure() {
		return id_agenda_manicure;
	}
	public void setId_agenda_manicure(Long id_agenda_manicure) {
		this.id_agenda_manicure = id_agenda_manicure;
	}
	
}
