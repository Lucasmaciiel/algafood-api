package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.model.FormaPagamento;
import com.lmg.lmgfood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl  implements FormaPagamentoRepository{

	@Override
	public List<FormaPagamento> buscarTodas() {
		return null;
	}

	@Override
	public FormaPagamento buscarPorId(Long id) {
		return null;
	}

	@Override
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return null;
	}

	@Override
	public void remover(FormaPagamento formaPagamento) {
		
	}

	

}
