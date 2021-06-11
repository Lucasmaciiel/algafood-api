package com.lmg.lmgfood.infra.repository;

import static com.lmg.lmgfood.infra.repository.specification.RestauranteSpecs.comFreteGratis;
import static com.lmg.lmgfood.infra.repository.specification.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.RestauranteRepository;
import com.lmg.lmgfood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries { // Consulta Dinamica

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	@Lazy
	private RestauranteRepository restauranteRepository;

	// Consulta dinâmica com criteria 
	@Override
	public List<Restaurante> buscarPorNomeETaxaFrete(String nome, BigDecimal taxaFreteInicial,
			BigDecimal taxaFreteFinal) {

		var builder = manager.getCriteriaBuilder();
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		Root<Restaurante> root = criteria.from(Restaurante.class);

		var predicates = new ArrayList<Predicate>();

		if (StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
		}
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));
		}
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));
		}

		criteria.where(predicates.toArray(new Predicate[0]));

		var query = manager.createQuery(criteria);
		return query.getResultList();
	}

	@Override
	public List<Restaurante> buscarComFreteGratis(String nome) {
		return restauranteRepository.findAll(comFreteGratis().and(comNomeSemelhante(nome)));
	}

}
