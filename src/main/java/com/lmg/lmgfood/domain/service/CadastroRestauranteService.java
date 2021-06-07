package com.lmg.lmgfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.CozinhaRepository;
import com.lmg.lmgfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CozinhaRepository cozinhaRepository;

	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}

	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Cozinha cozinha = cozinhaRepository.findById(cozinhaId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de cozinha com o código %d ", cozinhaId)));

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}

}
