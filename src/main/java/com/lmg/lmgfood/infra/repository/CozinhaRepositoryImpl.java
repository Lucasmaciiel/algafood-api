package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.repository.CozinhaRepository;

@Repository
public class CozinhaRepositoryImpl  implements CozinhaRepository{

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
	public Cozinha remover(Cozinha cozinha) {
		return null;
	}
	

}
