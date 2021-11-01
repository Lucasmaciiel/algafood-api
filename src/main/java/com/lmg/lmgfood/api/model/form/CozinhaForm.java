package com.lmg.lmgfood.api.model.form;

import com.lmg.lmgfood.domain.model.Restaurante;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CozinhaForm {

    private String nome;

    private List<Restaurante> restaurantes = new ArrayList<>();

}
