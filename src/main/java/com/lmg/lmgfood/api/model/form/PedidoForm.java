package com.lmg.lmgfood.api.model.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class PedidoForm {

    @Valid
    @NotNull
    private RestauranteIdForm restaurante;

    @Valid
    @NotNull
    private EnderecoForm enderecoEntrega;

    @Valid
    @NotNull
    private FormaPagamentoIdForm formaPagamento;

    @Valid
    @NotNull
    @Size(min = 1)
    private List<ItemPedidoForm> itens;

}
