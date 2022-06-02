package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.UsuarioMapper;
import com.lmg.lmgfood.api.model.UsuarioDTO;
import com.lmg.lmgfood.api.model.form.AlterarSenhaUsuarioForm;
import com.lmg.lmgfood.api.model.form.AlterarUsuarioForm;
import com.lmg.lmgfood.api.model.form.UsuarioForm;
import com.lmg.lmgfood.domain.model.Usuario;
import com.lmg.lmgfood.domain.repository.UsuarioRepository;
import com.lmg.lmgfood.domain.service.CadastroUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CadastroUsuarioService cadastroUsuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @GetMapping
    public List<UsuarioDTO> listar() {
        var usuarios = usuarioRepository.findAll();
        return usuarioMapper.toList(usuarios);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDTO buscarPorId(@PathVariable Long usuarioId) {
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        return usuarioMapper.toDTO(usuario);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioDTO adicionar(@RequestBody @Valid UsuarioForm usuarioForm) {
        Usuario usuario = usuarioMapper.toDomainObject(usuarioForm);

        usuario = cadastroUsuarioService.adicionar(usuario);

        return usuarioMapper.toDTO(usuario);
    }

    @PutMapping("/{usuarioId}")
    public UsuarioDTO atualizar(@PathVariable Long usuarioId,
                                @RequestBody @Valid AlterarUsuarioForm alterarUsuarioForm) {
        Usuario usuarioAtual = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        usuarioMapper.copyToDomainObject(alterarUsuarioForm, usuarioAtual);

        usuarioAtual = cadastroUsuarioService.adicionar(usuarioAtual);

        return usuarioMapper.toDTO(usuarioAtual);
    }

    @PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId,
                               @RequestBody @Valid AlterarSenhaUsuarioForm alterarSenhaUsuarioForm) {

       cadastroUsuarioService.alterarSenha(usuarioId,
               alterarSenhaUsuarioForm.getSenhaAtual(),
               alterarSenhaUsuarioForm.getNovaSenha());

    }

    @DeleteMapping("/{usuarioId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long usuarioId) {
        cadastroUsuarioService.remover(usuarioId);
    }
}
