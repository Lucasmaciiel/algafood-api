package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.RestauranteDTO;
import com.lmg.lmgfood.api.model.form.RestauranteForm;
import com.lmg.lmgfood.domain.model.Restaurante;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteMapper {

    @Autowired
    private ModelMapper modelMapper;

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toDTO(restaurante))
                .collect(Collectors.toList());
    }

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    public Restaurante toDomain(RestauranteForm restauranteForm) {
        return modelMapper.map(restauranteForm, Restaurante.class);
    }
}
