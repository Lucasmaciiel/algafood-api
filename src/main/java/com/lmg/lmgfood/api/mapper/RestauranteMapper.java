package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.CozinhaDTO;
import com.lmg.lmgfood.api.model.RestauranteDTO;
import com.lmg.lmgfood.api.model.form.RestauranteForm;
import com.lmg.lmgfood.domain.model.Cozinha;
import com.lmg.lmgfood.domain.model.Restaurante;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RestauranteMapper {

    public List<RestauranteDTO> toCollectionDTO(List<Restaurante> restaurantes) {
        return restaurantes.stream()
                .map(restaurante -> toDTO(restaurante))
                .collect(Collectors.toList());
    }

    public RestauranteDTO toDTO(Restaurante restaurante) {
        CozinhaDTO cozinhaDTO = new CozinhaDTO();
        cozinhaDTO.setId(restaurante.getCozinha().getId());
        cozinhaDTO.setNome(restaurante.getCozinha().getNome());

        RestauranteDTO dto = new RestauranteDTO();
        dto.setId(restaurante.getId());
        dto.setNome(restaurante.getNome());
        dto.setTaxaFrete(restaurante.getTaxaFrete());
        dto.setCozinha(cozinhaDTO);
        return dto;
    }

    public Restaurante toDomain(RestauranteForm restauranteForm) {
        Cozinha cozinha = new Cozinha();
        cozinha.setId(restauranteForm.getCozinhaId().getId());

        Restaurante restaurante = new Restaurante();
        restaurante.setNome(restauranteForm.getNome());
        restaurante.setTaxaFrete(restauranteForm.getTaxaFrete());
        restaurante.setCozinha(cozinha);

        return restaurante;
    }
}
