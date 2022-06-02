package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AlterarSenhaUsuarioForm {

    @NotBlank
    private String senhaAtual;

    @NotBlank
    private String novaSenha;


}
