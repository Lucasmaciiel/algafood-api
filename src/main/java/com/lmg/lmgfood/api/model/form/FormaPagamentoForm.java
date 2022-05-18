package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class FormaPagamentoForm {

	@NotBlank(message = "Descrição é obrigatório")
	private String descricao;
	
}
