package com.lmg.lmgfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = -8138447204879283514L;


    public PedidoNaoEncontradoException(String codigoPedido){
        super(String.format("Não existe um pedido com código %s", codigoPedido));
    }

}
