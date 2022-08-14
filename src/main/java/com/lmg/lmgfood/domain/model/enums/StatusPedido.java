package com.lmg.lmgfood.domain.model.enums;

import java.util.Arrays;
import java.util.List;

public enum StatusPedido {
	
	CRIADO("Criado"),
	CONFIRMADO("Confirmado", CRIADO),
	ENTREGUE("Entregue", CONFIRMADO),
	CANCELADO("Cancelado", CRIADO);

	private String descricao;
	private List<StatusPedido> statusAnteriores;

	StatusPedido(String descricao, StatusPedido... statusAnteriores){
		this.descricao = descricao;
		this.statusAnteriores = Arrays.asList(statusAnteriores);
	}

	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Verifica se o novo status pode ser alterado
	 * @param novoStatus
	 * @return Retorna true caso não esteja na lista de status anteriores
	 */
	public boolean naoPodeAlterarPara(StatusPedido novoStatus){
		return !novoStatus.statusAnteriores.contains(this);
	}
}
