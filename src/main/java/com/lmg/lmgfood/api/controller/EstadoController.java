package com.lmg.lmgfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
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
	public Estado adicionar(@RequestBody Estado estado) {
		return cadastroEstadoService.adicionar(estado);
	}

	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscarPorId(@PathVariable Long estadoId) {
		Estado estadoEncontrado = cadastroEstadoService.buscarPorId(estadoId);
		
		if (estadoEncontrado != null) {
			return ResponseEntity.ok(estadoEncontrado);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstadoService.remover(estadoId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
