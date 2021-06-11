package com.lmg.lmgfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.lmg.lmgfood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> buscarPorNomeETaxaFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> buscarComFreteGratis(String nome);

}