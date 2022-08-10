package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.UsuarioMapper;
import com.lmg.lmgfood.api.model.UsuarioDTO;
import com.lmg.lmgfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis")
public class RestauranteUsuarioResponsavelController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @GetMapping
    private List<UsuarioDTO> listar(@PathVariable Long restauranteId){
        var restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        return usuarioMapper.toList(restaurante.getResponsaveis());
    }

    @DeleteMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        cadastroRestauranteService.desassociarResponsavel(restauranteId, responsavelId);
    }

    @PutMapping("/{responsavelId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long restauranteId, @PathVariable Long responsavelId) {
        cadastroRestauranteService.associarResponsavel(restauranteId, responsavelId);
    }
}
