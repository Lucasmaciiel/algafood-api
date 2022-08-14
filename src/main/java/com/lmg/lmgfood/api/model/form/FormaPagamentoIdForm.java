package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class FormaPagamentoIdForm {

    @NotNull
    private Long id;
}
