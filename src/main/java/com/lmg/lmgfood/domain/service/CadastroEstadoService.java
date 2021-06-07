package com.lmg.lmgfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Estado;
import com.lmg.lmgfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> buscarTodos() {
		return estadoRepository.findAll();
	}

	public Estado buscarPorId(Long id) {
		return estadoRepository.findById(id).get();
	}

	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}

	public void remover(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
		} catch (EmptyResultDataAccessException e) {
			throw new EntidadeNaoEncontradaException(
					String.format("Não existe um cadastro de estado com o código %d", estadoId));
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("Estado de código %d não pode ser removido, pois está em uso", estadoId));
		}

	}
}
