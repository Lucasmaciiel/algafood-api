package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoForm {

    @NotBlank
    private String nome;

    @NotBlank
    @Email
    private String descricao;

    @NotNull
    @PositiveOrZero
    private BigDecimal preco;

    @NotNull
    private Boolean ativo;

}
