package com.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agendamento.model.Manicure;

public interface ManicureRepository extends JpaRepository<Manicure, Long> {

}
