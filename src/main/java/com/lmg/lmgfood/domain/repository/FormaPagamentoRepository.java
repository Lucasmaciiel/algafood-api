package com.lmg.lmgfood.domain.repository;

import java.util.List;

import com.lmg.lmgfood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	 List<FormaPagamento> buscarTodas();
	 
	 FormaPagamento buscarPorId(Long id);
	
	 FormaPagamento adicionar(FormaPagamento formaPagamento);
	 
	 void remover(FormaPagamento formaPagamento);
}
