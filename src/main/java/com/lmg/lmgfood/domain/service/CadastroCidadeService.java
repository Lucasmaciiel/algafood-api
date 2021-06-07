package com.lmg.lmgfood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.model.Estado;
import com.lmg.lmgfood.domain.repository.CidadeRepository;
import com.lmg.lmgfood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> buscarTodas() {
		return cidadeRepository.findAll();
	}

	public Cidade adicionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format("Não existe cadastro de estado com o código %d ", estadoId)));

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public void remover(Long id) {
		cidadeRepository.findById(id).orElseThrow( () -> 
		new EntidadeNaoEncontradaException(String
				.format("Não foi possível remover, pois a cidade de código %d não existe no banco de dados", id)));
		
		cidadeRepository.deleteById(id);
	}

	public Cidade buscarPorId(Long id) {
		return cidadeRepository.findById(id).get();
	}

}
