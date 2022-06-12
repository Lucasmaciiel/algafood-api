
package com.lmg.lmgfood.domain.exception;

public class ProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = 1L;

	public ProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}

	public ProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		this(String.format("Não existe um cadastro de produto com o código %d para o restaurante de código %d ", produtoId, restauranteId));
	}
}
