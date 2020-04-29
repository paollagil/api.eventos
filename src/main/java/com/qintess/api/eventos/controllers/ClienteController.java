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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.eventos.models.Cliente;
import com.qintess.api.eventos.models.Login;
import com.qintess.api.eventos.repositories.ClienteRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Clientes")
@CrossOrigin(origins="*")
public class ClienteController {

	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	@ApiOperation("Busca uma lista de todos os clientes cadastrados")
	public List<Cliente> listaClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping("/cliente/{id}")
	@ApiOperation("Busca um cliente cadastrado por id")
	public Cliente buscaCliente(@PathVariable(value="id") long id){
		return clienteRepository.findById(id);
	}
	
	@PostMapping("/cliente")
	@ApiOperation("Insere um cliente")
	public String insereCliente(@RequestBody Cliente cliente) {
		String response = "{\"criado\":";
		if(clienteRepository.save(cliente) != null) {
			response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
	@DeleteMapping("/cliente/{id}")
	@ApiOperation("Deleta um cliente cadastrado por id")
	public String deletaCliente(@PathVariable(value="id") long id) {
		String response = "{\"deletado\":";
		if(clienteRepository.deleteById(id) != null) {
			response += String.valueOf(false);
		}else {
			response += String.valueOf(true);
		}
		response += "}";
		return response;
	}
	
	@RequestMapping(value="/cliente/{id}", method=RequestMethod.PUT)
	public String atualizaCliente(@RequestBody Cliente cliente, @PathVariable(value="id") long id) {
	    Cliente clienteOriginal = clienteRepository.findById(id);
	    clienteOriginal.setNome(cliente.getNome());
	    clienteOriginal.setEmail(cliente.getEmail());
	    clienteOriginal.setCpf(cliente.getCpf());
	    clienteOriginal.setSenha(cliente.getSenha());
	    clienteOriginal.setIdCliente(id);
	    String response = "{\"alterado\":";
	    if(clienteRepository.save(clienteOriginal) != null){
	    	response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
	@PostMapping("/cliente/login")
	@ApiOperation("Busca se um cliente é válido por email e senha")
	public String buscaClienteValido(@RequestBody Login login){
		String response = "{\"autenticado\":";
		if(clienteRepository.findByLogin(login.getEmail(), login.getSenha()) > 0) {
			response += String.valueOf(true);
			response += ",";
			Cliente cliente = clienteRepository.findByEmail(login.getEmail());
			response += "\"idCliente\": ";
			response += String.valueOf(cliente.getIdCliente());
		}else {
			response += String.valueOf(false);
			response += ",";
			response += "\"idCliente\": 0";
		}
		response += "}";
		return response;
	}
}
