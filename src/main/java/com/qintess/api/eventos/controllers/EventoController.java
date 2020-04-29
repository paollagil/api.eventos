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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qintess.api.eventos.models.Evento;
import com.qintess.api.eventos.repositories.EventoRepository;
import com.qintess.api.eventos.repositories.LocalRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/api")
@Api(value = "API Eventos")
@CrossOrigin(origins = "*")
public class EventoController {

	@Autowired
	EventoRepository eventoRepository;
	
	@Autowired
	LocalRepository localRepository;

	@RequestMapping(value="/eventos", method=RequestMethod.GET)
	@ApiOperation("Busca uma lista de todos os eventos cadastrados")
	public List<Evento> listaEventos() {
		return eventoRepository.findAll();
	}
	
	@RequestMapping(value="/eventos/disponiveis", method=RequestMethod.GET)
	@ApiOperation("Busca uma lista de todos os eventos disponíveis cadastrados")
	public List<Evento> listaEventosDisponiveis() {
		return eventoRepository.findByDataLimiteGreaterThanEqual(new Date(System.currentTimeMillis()-24*60*60*1000));
		//return eventoRepository.listEventosDisponiveis();
	}

	@RequestMapping(value="/evento/{id}", method=RequestMethod.GET)
	@ApiOperation("Busca um evento cadastrado por id")
	public Evento buscaEvento(@PathVariable(value = "id") long id) {
		return eventoRepository.findById(id);
	}

	@RequestMapping(value="/evento", method=RequestMethod.POST)
	@ApiOperation("Insere um evento")
	public String insereEvento(@RequestBody Evento evento) {
		String response = "{\"criado\":";
		if(eventoRepository.save(evento) != null) {
			response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}

	@RequestMapping(value="/evento/{id}", method=RequestMethod.DELETE)
	@ApiOperation("Deleta um evento cadastrado por id")
	public String deletaEvento(@PathVariable(value = "id") long id) {
		String response = "{\"deletado\":";
		if(eventoRepository.deleteById(id) != null) {
			response += String.valueOf(false);
		}else {
			response += String.valueOf(true);
		}
		response += "}";
		return response;
	}

	@RequestMapping(value="/evento/{id}", method=RequestMethod.PUT)
	public String atualizaEvento(@RequestBody Evento evento, @PathVariable(value="id") long id) {
	    Evento eventoOriginal = eventoRepository.findById(id);
	    eventoOriginal.setNome(evento.getNome());
	    eventoOriginal.setDescricao(evento.getDescricao());
	    eventoOriginal.setDataHora(evento.getDataHora());
	    eventoOriginal.setQuantidadeIngresso(evento.getQuantidadeIngresso());
	    eventoOriginal.setValorIngresso(evento.getValorIngresso());
	    eventoOriginal.setIndicador(evento.getIndicador());
	    eventoOriginal.setDataLimite(evento.getDataLimite());
	    eventoOriginal.setLocal(evento.getLocal());
	    String response = "{\"alterado\":";
	    if(eventoRepository.save(eventoOriginal) != null){
	    	response += String.valueOf(true);
		}else {
			response += String.valueOf(false);
		}
		response += "}";
		return response;
	}
	
	
}
