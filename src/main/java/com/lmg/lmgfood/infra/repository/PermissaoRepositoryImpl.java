package com.lmg.lmgfood.infra.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lmg.lmgfood.domain.model.Permissao;
import com.lmg.lmgfood.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryImpl  implements PermissaoRepository{

	@Override
	public List<Permissao> buscarTodas() {
		return null;
	}

	@Override
	public Permissao buscarPorId(Long id) {
		return null;
	}

	@Override
	public Permissao adicionar(Permissao permissao) {
		return null;
	}

	@Override
	public Permissao remover(Permissao permissao) {
		return null;
	}


	

}
