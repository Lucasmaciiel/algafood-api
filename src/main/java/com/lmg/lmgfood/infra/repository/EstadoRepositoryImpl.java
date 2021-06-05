package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.repository.EstadoRepository;

import br.com.caelum.stella.type.Estado;

@Repository
public class EstadoRepositoryImpl  implements EstadoRepository{

	@Override
	public List<Estado> buscarTodos() {
		return null;
	}

	@Override
	public Estado buscarPorId(Long id) {
		return null;
	}

	@Override
	public Estado adicionar(Estado estado) {
		return null;
	}

	@Override
	public Estado remover(Estado estado) {
		return null;
	}

	

}
