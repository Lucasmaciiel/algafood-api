package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.GrupoMapper;
import com.lmg.lmgfood.api.model.GrupoDTO;
import com.lmg.lmgfood.api.model.form.GrupoForm;
import com.lmg.lmgfood.domain.model.Grupo;
import com.lmg.lmgfood.domain.repository.GrupoRepository;
import com.lmg.lmgfood.domain.service.CadastroGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private CadastroGrupoService cadastroGrupo;

    @Autowired
    private GrupoMapper grupoMapper;


    @GetMapping
    public List<GrupoDTO> listar() {
        List<Grupo> todosGrupos = grupoRepository.findAll();

        return grupoMapper.toList(todosGrupos);
    }

    @GetMapping("/{grupoId}")
    public GrupoDTO buscar(@PathVariable Long grupoId) {
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        return grupoMapper.toDTO(grupo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoDTO adicionar(@RequestBody @Valid GrupoForm grupoInput) {
        Grupo grupo = grupoMapper.toDomainObject(grupoInput);

        grupo = cadastroGrupo.salvar(grupo);

        return grupoMapper.toDTO(grupo);
    }

    @PutMapping("/{grupoId}")
    public GrupoDTO atualizar(@PathVariable Long grupoId,
                              @RequestBody @Valid GrupoForm grupoInput) {
        Grupo grupoAtual = cadastroGrupo.buscarOuFalhar(grupoId);

        grupoMapper.copyToDomainObject(grupoInput, grupoAtual);

        grupoAtual = cadastroGrupo.salvar(grupoAtual);

        return grupoMapper.toDTO(grupoAtual);
    }

    @DeleteMapping("/{grupoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long grupoId) {
        cadastroGrupo.excluir(grupoId);
    }
}