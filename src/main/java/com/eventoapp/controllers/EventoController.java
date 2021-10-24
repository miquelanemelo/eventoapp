package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.service.EventoService;

@Controller
@RequestMapping("eventos")
public class EventoController {

	@Autowired
	private EventoService service;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView model = new ModelAndView("index");
		Iterable<Evento> eventos = service.buscarTodos();
		model.addObject("eventos", eventos);
		return model;
	}

	@GetMapping("/cadastro")
	public String form(Model model) {
		model.addAttribute("evento", new Evento());
		return "evento/formEvento";
	}

	@PostMapping("/cadastrar")
	public String form(Evento evento) {
		service.salvar(evento);
		return "redirect:/eventos/";
	}

	@GetMapping("/{id}")
	public ModelAndView detalhesEventos(@PathVariable("id") Long id) {
		ModelAndView model = new ModelAndView("evento/detalhesEvento");
		Evento evento = service.buscarPorId(id);
		model.addObject("evento", evento);
		model.addObject("convidaddos", evento.getConvidados());
		return model;
	}

	@PostMapping("/{id}")
	public String adicionarConvidado(@PathVariable("id") Long id, Convidado convidado) {
		Evento evento = service.buscarPorId(id);
		service.adicionarConvidado(evento, convidado);
		return "redirect:/eventos/{id}";
	}

}
