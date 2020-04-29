package com.qintess.api.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qintess.api.eventos.models.Ingresso;

public interface IngressoRepository extends JpaRepository<Ingresso, Long>{
	
	Ingresso findById(long id);
	Ingresso deleteById(long id);
	
	@Query("Select count(*) from Ingresso WHERE evento_id = :id")
	int countByEvento(long id);

}
