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

	private static final String NÃO_EXISTE_CIDADE_COM_O_CÓDIGO_PESQUISADO = "Não foi possível encontrar cidade com o código %d ";

	private static final String MSG_ESTADO_NAO_ENCONTRADO = "Não existe cadastro de estado com o código %d ";

	private static final String MSG_NAO_FOI_POSSIVEL_REMOVER_CIDADE = "Não foi possível remover, pois a cidade de código %d não existe no banco de dados";

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;

	public List<Cidade> buscarTodas() {
		return cidadeRepository.findAll();
	}

	public Cidade adicionar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Estado estado = estadoRepository.findById(estadoId).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));

		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
	}

	public void remover(Long id) {
		cidadeRepository.findById(id).orElseThrow(
				() -> new EntidadeNaoEncontradaException(String.format(MSG_NAO_FOI_POSSIVEL_REMOVER_CIDADE, id)));

		cidadeRepository.deleteById(id);
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) {
		return cidadeRepository.findById(cidadeId).orElseThrow(() -> new EntidadeNaoEncontradaException(
				String.format(NÃO_EXISTE_CIDADE_COM_O_CÓDIGO_PESQUISADO, cidadeId)));
	}
}
