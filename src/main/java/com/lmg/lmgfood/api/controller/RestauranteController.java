package com.lmg.lmgfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.RestauranteRepository;
import com.lmg.lmgfood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;

	@Autowired
	private RestauranteRepository restauranteRepository;

	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
		try {
			return ResponseEntity.ok(cadastroRestauranteService.adicionar(restaurante));
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}

	}

	@PutMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> atualizar(@RequestBody Restaurante restaurante,
			@PathVariable Long restauranteId) {
		try {
			Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(restauranteId);

			if (restauranteEncontrado.isPresent()) {
				BeanUtils.copyProperties(restaurante, restauranteEncontrado.get(), "id"); // ignora a copia do ID
				return ResponseEntity.ok(cadastroRestauranteService.adicionar(restauranteEncontrado.get()));
			}
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping
	public List<Restaurante> buscarTodos() {
		return cadastroRestauranteService.buscarTodos();
	}

	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscarPorId(@PathVariable Long restauranteId) {
		Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(restauranteId);
		if (restauranteEncontrado.isPresent()) {
			return ResponseEntity.ok(restauranteEncontrado.get());
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@PatchMapping("/{restauranteId}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos) {
		Optional<Restaurante> restauranteEncontrado = restauranteRepository.findById(restauranteId);
		
		if(restauranteEncontrado.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteEncontrado.get());
		
		return atualizar(restauranteEncontrado.get(), restauranteId);
	}

	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);
		
		dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
			field.setAccessible(true);
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);
			
			ReflectionUtils.setField(field, restauranteDestino, novoValor);
		});
	}	
}

























