package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.GrupoDTO;
import com.lmg.lmgfood.api.model.form.GrupoForm;
import com.lmg.lmgfood.domain.model.Grupo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GrupoMapper {

    @Autowired
    private ModelMapper modelMapper;

    //Assembler
    public GrupoDTO toDTO(Grupo grupo) {
        return modelMapper.map(grupo, GrupoDTO.class);
    }

    public List<GrupoDTO> toList(Collection<Grupo> grupos) {
        return grupos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    //disassembler
    public Grupo toDomainObject(GrupoForm grupoForm) {
        return modelMapper.map(grupoForm, Grupo.class);
    }

    public void copyToDomainObject(GrupoForm grupoForm, Grupo grupo) {
        modelMapper.map(grupoForm, grupo);
    }
}
