package com.eventoapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Evento;
import com.eventoapp.repository.EventoRepository;

@Controller
@RequestMapping("eventos")
public class EventoController {

	@Autowired
	private EventoRepository repository;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		Iterable<Evento> eventos = repository.findAll();
		model.addObject("eventos", eventos);
		return model;
	}

	@GetMapping("/cadastro")
	public String form(Model model) {
		model.addAttribute("evento", new Evento());
		return "evento/formEvento";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String form(Evento evento) {
		repository.save(evento);
		return "redirect:/cadastro";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ModelAndView detalhesEventos(@PathVariable("id") Long id) {
		Optional<Evento> evento = repository.findById(id);
		ModelAndView model = new ModelAndView("evento/detalhesEvento");
		model.addObject("evento", evento.get());
		return model;
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.POST) public String
	 * detalhesEventosPost(@PathVariable("id") long id, Convidado convidado) {
	 * Evento evento = eventoRepository.findByCodigo(id);
	 * convidado.setEvento(evento); cr.save(convidado); return "redirect:/{id}"; }
	 */

}
