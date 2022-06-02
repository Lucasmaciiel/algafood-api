package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AlterarUsuarioForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String email;


}
