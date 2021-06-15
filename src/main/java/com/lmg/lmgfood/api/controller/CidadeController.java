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
	public Cidade atualizar(@RequestBody Cidade cidade, @PathVariable Long cidadeId) {
		Cidade cidadeEncontrada = cadastroCidadeService.buscarOuFalhar(cidadeId);

		BeanUtils.copyProperties(cidade, cidadeEncontrada, "id"); // ignora a copia do ID
		return cadastroCidadeService.adicionar(cidadeEncontrada);
	}

	@GetMapping
	public List<Cidade> buscarTodas() {
		return cadastroCidadeService.buscarTodas();
	}

	@GetMapping("/{cidadeId}")
	public Cidade buscarPorId(@PathVariable Long cidadeId) {
		return cadastroCidadeService.buscarOuFalhar(cidadeId);
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.remover(cidadeId);
	}
}
