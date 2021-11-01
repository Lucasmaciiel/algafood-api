package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.CidadeMapper;
import com.lmg.lmgfood.api.model.CidadeDTO;
import com.lmg.lmgfood.api.model.form.CidadeForm;
import com.lmg.lmgfood.domain.exception.EstadoNaoEncontradoException;
import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.service.CadastroCidadeService;
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

import java.util.List;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CidadeMapper mapper;

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public CidadeDTO adicionar(@RequestBody CidadeForm cidadeForm) {
		try {
			var cidade = mapper.toDomainObject(cidadeForm);
			return mapper.toDTO(cadastroCidadeService.adicionar(cidade));

		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@PutMapping("/{cidadeId}")
	public CidadeDTO atualizar(@RequestBody CidadeForm cidadeForm, @PathVariable Long cidadeId) {
		Cidade cidadeEncontrada = cadastroCidadeService.buscarOuFalhar(cidadeId);

		mapper.copyToDomainObject(cidadeForm, cidadeEncontrada);
		try {
			return mapper.toDTO(cadastroCidadeService.adicionar(cidadeEncontrada));
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}

	@GetMapping
	//TODO: Verificar
	public List<Cidade> buscarTodas() {
		return cadastroCidadeService.buscarTodas();
	}

	@GetMapping("/{cidadeId}")
	public CidadeDTO buscarPorId(@PathVariable Long cidadeId) {
		return mapper.toDTO(cadastroCidadeService.buscarOuFalhar(cidadeId));
	}

	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) {
		cadastroCidadeService.remover(cidadeId);
	}
}
