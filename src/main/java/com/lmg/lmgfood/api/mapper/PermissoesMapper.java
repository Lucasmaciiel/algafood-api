package com.lmg.lmgfood.api.mapper;


import com.lmg.lmgfood.api.model.PermissaoDTO;
import com.lmg.lmgfood.domain.model.Permissao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PermissoesMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PermissaoDTO toDTO(Permissao permissao){
        return modelMapper.map(permissao, PermissaoDTO.class);
    }
    public List<PermissaoDTO> toCollectionModel(Collection<Permissao> permissoes){
        return permissoes.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
