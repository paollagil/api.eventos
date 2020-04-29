package com.qintess.api.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qintess.api.eventos.models.Administrador;

public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
	
	Administrador findById(long id);
	Administrador deleteById(long id);

}
