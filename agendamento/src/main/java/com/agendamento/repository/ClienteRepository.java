package com.agendamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.agendamento.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	@Query(value="SELECT A.* FROM CLIENTE A "
			+ " WHERE A.id_facebook = :idFacebook",nativeQuery=true)
	Cliente findByIdFacebook(@Param("idFacebook") String idFacebook);
}
