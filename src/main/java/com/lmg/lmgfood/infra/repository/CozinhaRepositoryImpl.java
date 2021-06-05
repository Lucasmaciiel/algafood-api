package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@Override
	public List<Cozinha> buscarTodas() {
		return null;
	}

	@Override
	public Cozinha buscarPorId(Long id) {
		return null;
	}

	@Override
	public Cozinha adicionar(Cozinha cozinha) {
		return null;
	}

	@Override
	public void remover(Long id) {
		Cozinha cozinha = buscarPorId(id);

		if (cozinha == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		//remove
	}

}
