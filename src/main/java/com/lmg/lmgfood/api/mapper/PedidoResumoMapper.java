package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.PedidoResumoDTO;
import com.lmg.lmgfood.domain.model.Pedido;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PedidoResumoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PedidoResumoDTO toDTO(Pedido pedido) {
        return modelMapper.map(pedido, PedidoResumoDTO.class);
    }

    public List<PedidoResumoDTO> toCollectionModel(List<Pedido> pedidos) {
        return pedidos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
