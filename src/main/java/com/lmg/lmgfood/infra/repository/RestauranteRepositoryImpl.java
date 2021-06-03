package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.RestauranteRepository;

@Repository
public class RestauranteRepositoryImpl  implements RestauranteRepository{

	@Override
	public List<Restaurante> buscarTodas() {
		return null;
	}

	@Override
	public Restaurante buscarPorId(Long id) {
		return null;
	}

	@Override
	public Restaurante adicionar(Restaurante restaurante) {
		return null;
	}

	@Override
	public Restaurante remover(Restaurante restaurante) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
