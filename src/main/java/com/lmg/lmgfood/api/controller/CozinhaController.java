package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.CozinhaMapper;
import com.lmg.lmgfood.api.model.CozinhaDTO;
import com.lmg.lmgfood.api.model.form.CozinhaForm;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.repository.CozinhaRepository;
import com.lmg.lmgfood.domain.service.CadastroCozinhaService;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/cozinhas")
public class CozinhaController {

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CozinhaMapper mapper;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	@GetMapping("/por-nome")
	public List<Cozinha> buscarPorNome(String nome) {
		return cozinhaRepository.findByNomeContaining(nome);
	}

	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public CozinhaDTO adicionar(@RequestBody @Valid CozinhaForm cozinhaForm) {
		var cozinha = mapper.toDomainObject(cozinhaForm);
		return mapper.toDTO(cadastroCozinhaService.adicionar(cozinha));
	}

	@PutMapping("/{cozinhaId}")
	public CozinhaDTO atualizar(@RequestBody CozinhaForm cozinhaForm, @PathVariable Long cozinhaId) {
		Cozinha cozinhaEncontrada = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		mapper.copyToDomainObject(cozinhaForm, cozinhaEncontrada);

		return mapper.toDTO(cadastroCozinhaService.adicionar(cozinhaEncontrada));

	}

	@GetMapping
	public List<Cozinha> buscarTodas() {
		return cozinhaRepository.findAll();
	}

	@GetMapping("/{cozinhaId}")
	public CozinhaDTO buscarPorId(@PathVariable Long cozinhaId) {
		return mapper.toDTO(cadastroCozinhaService.buscarOuFalhar(cozinhaId));
	}

	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) {
		cadastroCozinhaService.remover(cozinhaId);
	}

}
