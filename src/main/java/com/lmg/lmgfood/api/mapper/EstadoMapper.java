package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.EstadoDTO;
import com.lmg.lmgfood.api.model.form.EstadoForm;
import com.lmg.lmgfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstadoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public EstadoDTO toDTO(Estado estado) {
        return modelMapper.map(estado, EstadoDTO.class);
    }


    public Estado toDomainObject(EstadoForm estadoForm) {
        return modelMapper.map(estadoForm, Estado.class);
    }

    public void copyToDomainObject(EstadoForm estadoForm, Estado estado){
        modelMapper.map(estadoForm, estado);
    }
}
