package com.lmg.lmgfood.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Embeddable // parte de uma outra entidade. incorporável
public class Endereco {

	@Column(name = "endereco_cep", nullable = false)
	private String cep;

	@Column(name = "endereco_logradouro", nullable = false)
	private String logadouro;

	@Column(name = "endereco_numero", nullable = false)
	private String numero;

	@Column(name = "endereco_complemento")
	private String complemente;
	
	@Column(name = "endereco_bairro", nullable = false)
	private String bairro;
	
	@JoinColumn(name = "endereco_cidade_id")
	@ManyToOne (fetch = FetchType.LAZY)
	private Cidade cidade;
}
