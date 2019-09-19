package com.agendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.agendamento.model.AgendaManicure;

public interface AgendaManicureRepository extends JpaRepository<AgendaManicure, Long>{
	
	@Query(value="SELECT  * " + 
				"FROM AGENDA_MANICURE " + 
				"WHERE STATUS_AGENDA = 'M'", nativeQuery = true)
	List<AgendaManicure> listaAgendamentoManicureDisponivel();

}
