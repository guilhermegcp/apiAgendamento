package com.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.model.Servico;

public interface ServicoRepository extends JpaRepository<Servico, Long>{
	

}
