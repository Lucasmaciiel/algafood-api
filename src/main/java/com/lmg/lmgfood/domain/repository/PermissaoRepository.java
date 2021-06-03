package com.lmg.lmgfood.domain.repository;

import java.util.List;

import com.lmg.lmgfood.domain.model.Permissao;

public interface PermissaoRepository {
	
	List<Permissao> buscarTodas();
	
	Permissao buscarPorId(Long id);
	
	Permissao adicionar(Permissao permissao);
	
	Permissao remover(Permissao permissao);

}
