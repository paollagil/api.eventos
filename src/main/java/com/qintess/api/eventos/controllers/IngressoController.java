package com.qintess.api.eventos.controllers;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.eventos.models.Cliente;
import com.qintess.api.eventos.models.Compra;
import com.qintess.api.eventos.models.Evento;
import com.qintess.api.eventos.models.Ingresso;
import com.qintess.api.eventos.repositories.ClienteRepository;
import com.qintess.api.eventos.repositories.EventoRepository;
import com.qintess.api.eventos.repositories.IngressoRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/api")
@Api(value="API Ingressos")
@CrossOrigin(origins="*")
public class IngressoController {

	@Autowired
	IngressoRepository ingressoRepository;
	
	@Autowired
	EventoRepository eventoRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/ingressos")
	@ApiOperation("Busca uma lista de todos os ingressos cadastrados")
	public List<Ingresso> listaIngressos(){
		return ingressoRepository.findAll();
	}
	
	@GetMapping("/ingresso/{id}")
	@ApiOperation("Busca um ingresso cadastrado por id")
	public Ingresso buscaIngresso(@PathVariable(value="id") long id){
		return ingressoRepository.findById(id);
	}
	
	@PostMapping("/ingresso")
	@ApiOperation("Insere um ingresso")
	public String insereIngresso(@RequestBody Ingresso ingresso) {
		ingressoRepository.save(ingresso);
		return "Ingresso cadastrado com sucesso!";
	}
	
	@DeleteMapping("/ingresso/{id}")
	@ApiOperation("Deleta um ingresso cadastrado por id")
	public String deletaIngresso(@PathVariable(value="id") long id) {
		ingressoRepository.deleteById(id);
		return "Ingresso deletado com sucesso!";
	}
	
	@PutMapping("/ingresso")
	@ApiOperation("Atualiza um ingresso cadastrado")
	public String atualizaIngresso(@RequestBody Ingresso ingresso) {
		ingressoRepository.save(ingresso);
		return "Ingresso alterado com sucesso!";
	}
	
	@GetMapping("/ingresso/disponiveis/{evento_id}")
	@ApiOperation("Busca ingressos disponíveis por evento")
	public String buscaIngressosDisponiveis(@PathVariable(value="evento_id") long id){
		Evento evento = eventoRepository.findById(id);
		int disponiveis = 0;
		int ingressosComprados = ingressoRepository.countByEvento(id);
		disponiveis = evento.getQuantidadeIngresso() - ingressosComprados;
		String response = "{\"quantidade\":";
		response += String.valueOf(disponiveis);
		response += "}";
		return response;
	}
	
	@PostMapping("/ingresso/compra")
	@ApiOperation("Insere um registro de ingresso comprado no cliente e no evento")
	public String insereIngressoComprado(@RequestBody Compra compra) {
		Cliente cliente = clienteRepository.findById(compra.getIdCliente());
		Evento evento = eventoRepository.findById(compra.getIdEvento());
		String response = "{\"criado\":";
		
		for (int i = 0; i < compra.getQt(); i++) {
			Ingresso ingresso = new Ingresso();
			ingresso.setCliente(cliente);
			ingresso.setEvento(evento);
			ingresso.setDataCompra(new Date());
			
			ingressoRepository.save(ingresso);
		}
		response += String.valueOf(true);
		
		response += "}";
		return response;
	}
}
