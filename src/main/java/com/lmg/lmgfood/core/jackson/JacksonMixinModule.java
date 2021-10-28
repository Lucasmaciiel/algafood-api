package com.lmg.lmgfood.core.jackson;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.module.SimpleModule;
import com.lmg.lmgfood.api.model.mixin.CidadeMixin;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.model.Restaurante;

@Component
public class JacksonMixinModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public JacksonMixinModule() {
		setMixInAnnotation(Cidade.class, CidadeMixin.class);
	}
}
