package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.CozinhaDTO;
import com.lmg.lmgfood.api.model.form.CozinhaForm;
import com.lmg.lmgfood.domain.model.Cozinha;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CozinhaMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CozinhaDTO toDTO(Cozinha cozinha){
        return modelMapper.map(cozinha, CozinhaDTO.class);
    }

    public Cozinha toDomainObject(CozinhaForm cozinhaForm) {
        return modelMapper.map(cozinhaForm, Cozinha.class);
    }

    public void copyToDomainObject(CozinhaForm cozinhaForm, Cozinha cozinha){
        modelMapper.map(cozinhaForm, cozinha);
    }
}
