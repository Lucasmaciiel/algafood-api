package com.lmg.lmgfood.api.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class EnderecoDTO {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemente;
	private String bairro;
	private CidadeResumoDTO cidade;
}
