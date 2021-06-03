package com.lmg.lmgfood.domain.repository;

import java.util.List;

import com.lmg.lmgfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> buscarTodas();
	
	Restaurante buscarPorId(Long id);
	
	Restaurante adicionar(Restaurante restaurante);
	
	Restaurante remover(Restaurante restaurante);

}
