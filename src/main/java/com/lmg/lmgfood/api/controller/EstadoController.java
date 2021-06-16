package com.lmg.lmgfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.lmg.lmgfood.domain.model.Estado;
import com.lmg.lmgfood.domain.repository.EstadoRepository;
import com.lmg.lmgfood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoController {

	@Autowired
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;

	@GetMapping
	public List<Estado> buscarTodas() {
		return estadoRepository.findAll();
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.adicionar(estado);
	}
	
	@PutMapping(value = "/{estadoId}")
	@ResponseStatus(value = HttpStatus.OK)
	public Estado atualizar(@RequestBody Estado estado, @PathVariable Long estadoId) {
		
		Estado estadoEncontrado = cadastroEstadoService.buscarOuFalhar(estadoId);

		BeanUtils.copyProperties(estado, estadoEncontrado, "id");
		return cadastroEstadoService.adicionar(estadoEncontrado);
	}
	
	
	@GetMapping("/{estadoId}")
	public Estado buscarPorId(@PathVariable Long estadoId) {
		return cadastroEstadoService.buscarOuFalhar(estadoId);
	}

	@DeleteMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) {
		cadastroEstadoService.remover(estadoId);
	}
}
