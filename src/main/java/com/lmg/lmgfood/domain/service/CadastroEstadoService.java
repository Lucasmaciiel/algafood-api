package com.lmg.lmgfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.exception.EstadoNaoEncontradoException;
import com.lmg.lmgfood.domain.model.Estado;
import com.lmg.lmgfood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {

	private static final String MSG_ESTADO_EM_USO = "Estado de código %d não pode ser removido, pois está em uso ";

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Estado> buscarTodos() {
		return estadoRepository.findAll();
	}

	@Transactional
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
	}

	@Transactional
	public void remover(Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_ESTADO_EM_USO, estadoId));
		}

	}

	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId).orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}

	@Transactional
	public Estado atualizar(Estado estadoId) {
		return estadoRepository.save(estadoId);
	}
}
