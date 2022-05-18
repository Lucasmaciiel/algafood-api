package com.lmg.lmgfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lmg.lmgfood.domain.exception.RestauranteNaoEncontradoException;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {

	@Autowired
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;

	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);

		restaurante.setCozinha(cozinha);

		return restauranteRepository.save(restaurante);
	}


    @Transactional
    public void ativar(Long restauranteId){
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        restaurante.ativar();
        //não é preciso chamar o save, pois o jpa ja reconhece que estamos em uma transação do banco de dados
    }

    @Transactional
    public void inativar(Long restauranteId){
        Restaurante restaurante = this.buscarOuFalhar(restauranteId);
        restaurante.inativar();
        //não é preciso chamar o save, pois o jpa ja reconhece que estamos em uma transação do banco de dados
    }

	public Restaurante buscarOuFalhar(Long restauranteId) {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException(restauranteId));
	}
}
