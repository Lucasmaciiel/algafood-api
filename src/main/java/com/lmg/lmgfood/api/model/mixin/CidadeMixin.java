package com.lmg.lmgfood.api.model.mixin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lmg.lmgfood.domain.model.Estado;


public class CidadeMixin {

	
	@JsonIgnoreProperties(value = "nome")
	private Estado estado;
}
