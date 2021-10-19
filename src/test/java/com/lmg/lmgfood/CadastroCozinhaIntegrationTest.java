package com.lmg.lmgfood;

import com.lmg.lmgfood.domain.exception.CozinhaNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.service.CadastroCozinhaService;
import javassist.NotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CadastroCozinhaIntegrationTest {

		@Autowired
		private CadastroCozinhaService service;

		@Test
		@DisplayName("Deve cadastrar uma cozinha com sucesso")
		void testarCadastroCozinhaComSucesso(){
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

		@Test
		@DisplayName("Deve remover uma cozinha com sucesso")
		void testarRemoverCozinhaComSucesso(){
			//cenário
			var cozinha =
					Cozinha.builder()
							.nome("Cozinha inglesa")
							.restaurantes(Arrays.asList(
									Restaurante.builder()
											.taxaFrete(new BigDecimal(10))
											.dataCadastro(LocalDateTime.now())
											.build()))
							.build();

			//ação Adiciona uma cozinha
			Cozinha cozinhaSalva = service.adicionar(cozinha);

			//validação que existe a cozinha
			assertThat(cozinhaSalva).isNotNull();
			assertThat(cozinhaSalva.getId()).isNotNull();

			//remove cozinha e testa se removeu
			service.remover(cozinha.getId());
			var cozinhaExcluida = cozinhaSalva.getId();

			assertThatExceptionOfType(CozinhaNaoEncontradaException.class)
					.isThrownBy( () -> service.buscarOuFalhar(cozinhaExcluida));

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
