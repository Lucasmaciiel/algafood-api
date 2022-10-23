package com.lmg.lmgfood.api.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensivel"),
	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada"),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
	ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado");

	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "http://localhost:8080" + path;
		this.title = title;
	}
}
