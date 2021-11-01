package com.lmg.lmgfood.api.model.form;

import com.lmg.lmgfood.domain.model.Estado;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CidadeForm {

    private String nome;

    private Estado estado;

}
