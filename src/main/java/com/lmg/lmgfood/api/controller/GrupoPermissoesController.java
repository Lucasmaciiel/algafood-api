package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.FormaPagamentoMapper;
import com.lmg.lmgfood.api.mapper.PermissoesMapper;
import com.lmg.lmgfood.api.model.FormaPagamentoDTO;
import com.lmg.lmgfood.api.model.PermissaoDTO;
import com.lmg.lmgfood.domain.model.Permissao;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.service.CadastroGrupoService;
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

import javax.validation.constraints.FutureOrPresent;
import java.util.List;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes")
public class GrupoPermissoesController {

    @Autowired
    private CadastroGrupoService cadastroGrupoService;

    @Autowired
    private PermissoesMapper mapper;

    @GetMapping
    public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
        var grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
        return mapper.toCollectionModel(grupo.getPermissoes());
    }

    @DeleteMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupoService.desassociarPermissao(grupoId, permissaoId);
    }


    @PutMapping("/{permissaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId){
        cadastroGrupoService.associarPermissao(grupoId, permissaoId);
    }


}
