package com.lmg.lmgfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@PostMapping
	public Cidade adicionar(@RequestBody Cidade cidade) {
		return cadastroCidadeService.adicionar(cidade);
	}
	

	@PutMapping("/{cidadeId}")
	public ResponseEntity<Cidade> atualizar(@RequestBody Cidade cidade,
			@PathVariable Long cidadeId) {
		try {
			Cidade cidadeEncontrada = cadastroCidadeService.buscarPorId(cidadeId);

			if (cidadeEncontrada != null) {
				BeanUtils.copyProperties(cidade, cidadeEncontrada, "id"); // ignora a copia do ID
				return ResponseEntity.ok(cadastroCidadeService.adicionar(cidadeEncontrada));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Cidade> buscarTodas() {
		return cadastroCidadeService.buscarTodas();
	}

	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscarPorId(@PathVariable Long cidadeId) {
		Cidade cidadeEncontrada = cadastroCidadeService.buscarPorId(cidadeId);
		
		if (cidadeEncontrada != null) {
			return ResponseEntity.ok(cidadeEncontrada);
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<?> remover(@PathVariable Long cidadeId) {
		try {
			cadastroCidadeService.remover(cidadeId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
}
