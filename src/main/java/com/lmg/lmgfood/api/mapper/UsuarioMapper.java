package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.UsuarioDTO;
import com.lmg.lmgfood.api.model.form.AlterarSenhaUsuarioForm;
import com.lmg.lmgfood.api.model.form.AlterarUsuarioForm;
import com.lmg.lmgfood.api.model.form.UsuarioForm;
import com.lmg.lmgfood.domain.model.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UsuarioMapper {

    @Autowired
    private ModelMapper modelMapper;

    //Assembler
    public UsuarioDTO toDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    public List<UsuarioDTO> toList(Collection<Usuario> usuarios) {
        return usuarios.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //disassembler
    public Usuario toDomainObject(UsuarioForm usuarioForm) {
        return modelMapper.map(usuarioForm, Usuario.class);
    }

    public void copyToDomainObject(AlterarUsuarioForm alterarUsuarioForm, Usuario usuario) {
        modelMapper.map(alterarUsuarioForm, usuario);
    }

    public void copy(AlterarSenhaUsuarioForm alterarSenhaUsuarioForm, Usuario usuarioAtual) {
        modelMapper.map(alterarSenhaUsuarioForm, usuarioAtual);
    }
}
