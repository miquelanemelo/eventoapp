package com.eventoapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;
import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;

@Service
public class EventoService {

	private final EventoRepository eventoRepository;
	private final ConvidadoRepository convidadoRepository;

	@Autowired
	public EventoService(EventoRepository eventoRepository, ConvidadoRepository convidadoRepository) {
		this.eventoRepository = eventoRepository;
		this.convidadoRepository = convidadoRepository;
	}

	public void salvar(Evento evento) {
		eventoRepository.save(evento);
	}

	public Iterable<Evento> buscarTodos() {
		return eventoRepository.findAll();
	}

	public Evento buscarPorId(Long id) {
		Optional<Evento> optional = eventoRepository.findById(id);
		return optional.get();
	}

	public void adicionarConvidado(Evento evento, Convidado convidado) {
		evento.adicionarConvidado(convidado);
		eventoRepository.save(evento);
	}

}
