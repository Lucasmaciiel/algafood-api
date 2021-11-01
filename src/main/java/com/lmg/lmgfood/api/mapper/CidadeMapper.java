package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.CidadeDTO;
import com.lmg.lmgfood.api.model.form.CidadeForm;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.model.Estado;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CidadeMapper {

    @Autowired
    private ModelMapper modelMapper;

    public CidadeDTO toDTO(Cidade cidade){
        return modelMapper.map(cidade, CidadeDTO.class);
    }

    public Cidade toDomainObject(CidadeForm cidadeForm) {
        return modelMapper.map(cidadeForm, Cidade.class);
    }

    public void copyToDomainObject(CidadeForm cidadeForm, Cidade cidade){
        cidade.setEstado(new Estado());
        modelMapper.map(cidadeForm, cidade);
    }
}
