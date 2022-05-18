package com.lmg.lmgfood.core.jackson.modelmapper;

import com.lmg.lmgfood.api.model.EnderecoDTO;
import com.lmg.lmgfood.domain.model.Endereco;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        var enderecoToDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        enderecoToDTOTypeMap.<String>addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value)
        );

        return modelMapper;
    }
}
