package com.lmg.lmgfood.core.jackson.modelmapper;

import com.lmg.lmgfood.api.model.EnderecoDTO;
import com.lmg.lmgfood.api.model.form.ItemPedidoForm;
import com.lmg.lmgfood.domain.model.Endereco;
import com.lmg.lmgfood.domain.model.ItemPedido;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();

        /*
            Pula o mapeamento do Id do ItemPedido
         */
        modelMapper.createTypeMap(ItemPedidoForm.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        var enderecoToDTOTypeMap = modelMapper.createTypeMap(Endereco.class, EnderecoDTO.class);

        enderecoToDTOTypeMap.<String>addMapping(
                src -> src.getCidade().getEstado().getNome(),
                (dest, value) -> dest.getCidade().setEstado(value)
        );

        return modelMapper;
    }
}
