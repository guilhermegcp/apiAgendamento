package com.agendamento.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agendamento.model.Atendimento;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long>{

	@Query(value="SELECT A.* FROM ATENDIMENTO A "
			+ " WHERE A.id_cliente = :idCliente",nativeQuery=true)
	List<Atendimento> listarAtendimentoPorCliente(@Param("idCliente") Long idCliente);


}
