package com.lmg.lmgfood.api.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lmg.lmgfood.api.mapper.RestauranteMapper;
import com.lmg.lmgfood.api.model.RestauranteDTO;
import com.lmg.lmgfood.api.model.form.RestauranteForm;
import com.lmg.lmgfood.domain.exception.CidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.exception.CozinhaNaoEncontradaException;
import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.service.CadastroRestauranteService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/restaurantes")
public class RestauranteController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private RestauranteMapper mapper;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public RestauranteDTO adicionar(@Valid @RequestBody RestauranteForm restauranteForm) {
        try {
            Restaurante restaurante = mapper.toDomainObject(restauranteForm);

            return mapper.toDTO(cadastroRestauranteService.adicionar(restaurante));
        } catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    public RestauranteDTO atualizar(@RequestBody @Valid RestauranteForm restauranteForm, @PathVariable Long restauranteId) {
        try {

            Restaurante restauranteEncontrado = cadastroRestauranteService.buscarOuFalhar(restauranteId);

            mapper.copyToDomainObject(restauranteForm, restauranteEncontrado);

            return mapper.toDTO(cadastroRestauranteService.adicionar(restauranteEncontrado));

        } catch (CozinhaNaoEncontradaException  | CidadeNaoEncontradaException e){
            throw new NegocioException(e.getMessage());
        }

    }

    @GetMapping
    public List<RestauranteDTO> buscarTodos() {
        return mapper.toCollectionDTO(cadastroRestauranteService.buscarTodos());
    }


    @GetMapping("/{restauranteId}")
    public RestauranteDTO buscarPorId(@PathVariable Long restauranteId) {
        return mapper.toDTO(cadastroRestauranteService.buscarOuFalhar(restauranteId));
    }


    // utilizando o PUT, pois é idempotente, não causará efeitos colaterais se fizer várias chamadas repetidas
    @PutMapping("/{restauranteId}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void ativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.ativar(restauranteId);
    }

    @DeleteMapping("/{restauranteId}/inativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Long restauranteId){
        cadastroRestauranteService.inativar(restauranteId);
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest serverHttpRequest = new ServletServerHttpRequest(request);

        try {
            var objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            var restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, serverHttpRequest);
        }
    }


}
