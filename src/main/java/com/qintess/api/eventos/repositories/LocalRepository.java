package com.qintess.api.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.api.eventos.models.Local;

public interface LocalRepository extends JpaRepository<Local, Long>{
		
		Local findById(long id);
		Local deleteById(long id);
	
}
