package com.eventoapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eventoapp.models.Convidado;
import com.eventoapp.repository.ConvidadoRepository;

@Service
public class ConvidadoService {

	private final ConvidadoRepository repository;

	@Autowired
	public ConvidadoService(ConvidadoRepository repository) {
		this.repository = repository;
	}
	
	public void salvar(Convidado convidado) {
		repository.save(convidado);
	}
	
	
	
}