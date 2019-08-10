package com.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
