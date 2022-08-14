package com.lmg.lmgfood.api.mapper;


import com.lmg.lmgfood.api.model.form.PedidoForm;
import com.lmg.lmgfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PedidoFormMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Pedido toDomainObject(PedidoForm pedidoForm){
        return modelMapper.map(pedidoForm, Pedido.class);
    }

    public void copyToDomainObject(PedidoForm pedidoForm, Pedido pedido){
        modelMapper.map(pedidoForm, pedido);
    }
}
