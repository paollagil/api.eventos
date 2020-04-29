package com.qintess.api.eventos.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qintess.api.eventos.models.Evento;

public interface EventoRepository extends JpaRepository<Evento, Long>{
	
	Evento findById(long id);
	Evento deleteById(long id);
	
	List<Evento> findByDataLimiteGreaterThanEqual(Date data);

}
