package com.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.model.Atendimento;;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>{

}
