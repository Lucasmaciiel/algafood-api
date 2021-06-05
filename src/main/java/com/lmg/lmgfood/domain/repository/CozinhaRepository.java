package com.lmg.lmgfood.domain.repository;

import java.util.List;

import com.lmg.lmgfood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> buscarTodas();
	
	Cozinha buscarPorId(Long id);
	
	Cozinha adicionar(Cozinha cozinha);
	
	void remover(Long id);

}
