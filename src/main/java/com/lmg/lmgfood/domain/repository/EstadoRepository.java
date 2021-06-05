package com.lmg.lmgfood.domain.repository;

import java.util.List;

import br.com.caelum.stella.type.Estado;

public interface EstadoRepository {
	
	List<Estado> buscarTodos();
	
	Estado buscarPorId(Long id);
	
	Estado adicionar(Estado estado);
	
	Estado remover(Estado estado);

}
