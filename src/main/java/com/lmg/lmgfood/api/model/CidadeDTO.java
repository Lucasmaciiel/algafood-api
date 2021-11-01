package com.lmg.lmgfood.api.model;

import com.lmg.lmgfood.domain.model.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeDTO {

    private Long id;

    private String nome;

    private Estado estado;

}
