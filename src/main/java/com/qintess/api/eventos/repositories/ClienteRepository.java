package com.qintess.api.eventos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.qintess.api.eventos.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	
	Cliente findById(long id);
	Cliente deleteById(long id);
	
	@Query("select count(*) from Cliente WHERE email = :email and senha = :senha")
	int findByLogin(String email, String senha);
	
	Cliente findByEmail(String email);

}
