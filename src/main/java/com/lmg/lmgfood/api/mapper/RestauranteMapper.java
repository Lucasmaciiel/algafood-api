package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.RestauranteDTO;
import com.lmg.lmgfood.api.model.form.RestauranteForm;
import com.lmg.lmgfood.domain.model.Cidade;
import com.lmg.lmgfood.domain.model.Cozinha;
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
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public RestauranteDTO toDTO(Restaurante restaurante) {
        return modelMapper.map(restaurante, RestauranteDTO.class);
    }

    /**
     * Método para desmonstar o objeto
     *
     * @param restauranteForm objeto de entrada
     * @return retorna objeto restaurante
     */
    public Restaurante toDomainObject(RestauranteForm restauranteForm) {
        return modelMapper.map(restauranteForm, Restaurante.class);
    }

    public void copyToDomainObject(RestauranteForm restauranteForm, Restaurante restaurante){
        // para evitar exception
        //"identifier of an instance of com.lmg.lmgfood.domain.model.Restaurante was altered from 1 to 2;
        // nested exception is org.hibernate.HibernateException: identifier of an instance of com.lmg.lmgfood.domain.model.Restaurante was altered from 1 to 2",
        restaurante.setCozinha(new Cozinha());

        if (restaurante.getEndereco() != null){
            restaurante.getEndereco().setCidade(new Cidade());
        }

        modelMapper.map(restauranteForm, restaurante);
    }


}
