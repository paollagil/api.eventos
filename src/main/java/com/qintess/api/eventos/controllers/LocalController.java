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

import com.qintess.api.eventos.models.Local;
import com.qintess.api.eventos.repositories.LocalRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Locais")
@CrossOrigin(origins="*")
public class LocalController {

	@Autowired
	LocalRepository localRepository;
	
	@GetMapping("/locais")
	@ApiOperation("Busca uma lista de todos os locais cadastrados")
	public List<Local> listaLocais(){
		return localRepository.findAll();
	}
	
	@GetMapping("/local/{id}")
	@ApiOperation("Busca um local cadastrado por id")
	public Local buscaLocal(@PathVariable(value="id") long id){
		return localRepository.findById(id);
	}
	
	@PostMapping("/local")
	@ApiOperation("Insere um local")
	public String insereLocal(@RequestBody Local local) {
		
		String response = "{\"criado\":";
		if(localRepository.save(local) != null) {
			response += String.valueOf(false);
		}else {
			response += String.valueOf(true);
		}
		response += "}";
		return response;
	}
	
	@RequestMapping(value="/local/{id}", method=RequestMethod.DELETE)
	@ApiOperation("Deleta um local cadastrado por id")
	public String deletaLocal(@PathVariable(value="id") long id) {
		String response = "{\"deletado\":";
		if(localRepository.deleteById(id) != null) {
			response += String.valueOf(false);
		}else {
			response += String.valueOf(true);
		}
		response += "}";
		return response;
	}
	
	@RequestMapping(value="/local/{id}", method=RequestMethod.PUT)
	public String atualizaLocal(@RequestBody Local local, @PathVariable(value="id") long id) {
	    Local localOriginal = localRepository.findById(id);
	    localOriginal.setNome(local.getNome());
	    localOriginal.setEndereco(local.getEndereco());
	    localOriginal.setCep(local.getCep());
	    localOriginal.setCapacidade(local.getCapacidade());
	    localOriginal.setIdLocal(id);
	    String response = "{\"alterado\":";
	    if(localRepository.save(localOriginal) != null){
	    	response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
}
