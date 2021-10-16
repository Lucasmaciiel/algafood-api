package com.lmg.lmgfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmg.lmgfood.domain.exception.CozinhaNaoEncontradaException;
import com.lmg.lmgfood.domain.exception.EntidadeEmUsoException;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {

	private static final String MSN_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}

	@Transactional
	public void remover(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(cozinhaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSN_COZINHA_EM_USO, cozinhaId));
		}

	}

	public Cozinha buscarOuFalhar(Long cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new CozinhaNaoEncontradaException(cozinhaId));
	}
}
