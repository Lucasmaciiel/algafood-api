package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnderecoForm {

    @NotBlank
    private String cep;

    @NotBlank
    private String logadouro;

    @NotBlank
    private String numero;

    private String complemente;

    @NotBlank
    private String bairro;

    @Valid
    @NotNull
    private CidadeIdForm cidade;


}
