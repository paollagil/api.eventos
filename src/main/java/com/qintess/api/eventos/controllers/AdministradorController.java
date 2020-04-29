package com.qintess.api.eventos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.eventos.models.Administrador;
import com.qintess.api.eventos.repositories.AdministradorRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Administradores")
@CrossOrigin(origins="*")
public class AdministradorController {
	
	@Autowired
	AdministradorRepository adminRepository;
	
	@GetMapping("/administradores")
	@ApiOperation("Busca uma lista de todos os administradores cadastrados")
	public List<Administrador> listaAdministradores(){
		return adminRepository.findAll();
	}
	
	@GetMapping("/administrador/{id}")
	@ApiOperation("Busca um administrador cadastrado por id")
	public Administrador buscaAdministrador(@PathVariable(value="id") long id){
		return adminRepository.findById(id);
	}
	
	@PostMapping("/administrador")
	@ApiOperation("Insere um administrador")
	public String insereAdministrador(@RequestBody Administrador administrador) {
		String response = "{\"criado\":";
		if(adminRepository.save(administrador) != null) {
			response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
	@DeleteMapping("/administrador/{id}")
	@ApiOperation("Deleta um administrador cadastrado por id")
	public String deletaAdministrador(@PathVariable(value="id") long id) {
		String response = "{\"deletado\":";
		if(adminRepository.deleteById(id) != null) {
			response += String.valueOf(false);
		}else {
			response += String.valueOf(true);
		}
		response += "}";
		return response;
	}
	
	@RequestMapping(value="/administrador/{id}", method=RequestMethod.PUT)
	public String atualizaAdministrador(@RequestBody Administrador admin, @PathVariable(value="id") long id) {
	    Administrador adminOriginal = adminRepository.findById(id);
	    adminOriginal.setNome(admin.getNome());
	    adminOriginal.setEmail(admin.getEmail());
	    adminOriginal.setSenha(admin.getSenha());
	    adminOriginal.setIdAdministrador(id);
	    String response = "{\"alterado\":";
	    if(adminRepository.save(adminOriginal) != null){
	    	response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
}
