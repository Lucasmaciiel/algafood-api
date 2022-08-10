package com.lmg.lmgfood.domain.service;

import java.util.List;

import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.model.FormaPagamento;
import com.lmg.lmgfood.domain.model.Usuario;
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

	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;

	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;

	public List<Restaurante> buscarTodos() {
		return restauranteRepository.findAll();
	}

	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		Long cozinhaId = restaurante.getCozinha().getId();
		Long cidadeId = restaurante.getEndereco().getCidade().getId();
		
		Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
		Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

		restaurante.setCozinha(cozinha);
		restaurante.getEndereco().setCidade(cidade);

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

	/**
	 * Desassociar forma de pagamento com o restaurante
	 * @param restauranteId
	 * @param formaPagamentoId forma de pagamento que deseja remover
	 */
	@Transactional
	public void desassociarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

		restaurante.removerFormaPagamento(formaPagamento);
	}

	@Transactional
	public void associarFormaPagamento(Long restauranteId, Long formaPagamentoId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);

		FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

		restaurante.adicionarFormaPagamento(formaPagamento);
	}

	@Transactional
	public void desassociarResponsavel(Long restauranteId, Long usuarioId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario responsavel = cadastroUsuarioService.buscarOuFalhar(usuarioId);

		restaurante.removerResponsavel(responsavel);
	}

	@Transactional
	public void associarResponsavel(Long restauranteId, Long usuarioId){
		Restaurante restaurante = buscarOuFalhar(restauranteId);
		Usuario responsavel = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		restaurante.adicionarResponsavel(responsavel);
	}

	/**
	 * Abre um restaurante, indicando que o restaurante se encontra aberto
	 * @param restauranteId
	 */
	@Transactional
	public void abrir(Long restauranteId) {
		var restaurante = this.buscarOuFalhar(restauranteId);
		restaurante.abrirRestaurante();
	}

	/**
	 * Fecha um restaurante, indicando que o restaurante se encontra fechado
	 * @param restauranteId
	 */
	@Transactional
	public void fechar(Long restauranteId) {
		var restaurante = this.buscarOuFalhar(restauranteId);
		restaurante.fecharRestaurante();
	}
}
