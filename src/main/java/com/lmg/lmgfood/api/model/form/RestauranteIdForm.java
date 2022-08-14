package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RestauranteIdForm {

    @NotNull
    private Long id;
}
