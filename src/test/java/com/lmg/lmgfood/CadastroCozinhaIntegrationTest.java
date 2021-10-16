package com.lmg.lmgfood;

import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.service.CadastroCozinhaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import javax.validation.ConstraintViolationException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTest {

		@Autowired
		private CadastroCozinhaService service;

		@Test
		public void testarCadastroCozinhaComSucesso(){
			//cenário
			var cozinha =
					Cozinha.builder()
					.nome("Cozinha paraguaçu")
					.restaurantes(Arrays.asList(
							Restaurante.builder()
									.taxaFrete(new BigDecimal(10))
									.dataCadastro(LocalDateTime.now())
									.build()))
					.build();

			//ação
			Cozinha cozinhaSalva = service.adicionar(cozinha);

			//validação
			assertThat(cozinhaSalva).isNotNull();
			assertThat(cozinhaSalva.getId()).isNotNull();

		}

//		@Test(expected = ConstraintViolationException.class)
//		public void deveFalharAoCadastrarCozinhaQuandoSemNome(){
//			var cozinha =
//					Cozinha.builder().build();
//
//			var cozinhaSalva = service.adicionar(cozinha);
//
//			assertThat(cozinhaSalva).isNull();
//
//		}
}
