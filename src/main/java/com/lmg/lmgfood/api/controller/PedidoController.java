package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.PedidoMapper;
import com.lmg.lmgfood.api.model.PedidoDTO;
import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.repository.PedidoRepository;
import com.lmg.lmgfood.domain.service.EmissaoPedidoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private EmissaoPedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;


    @GetMapping
    public List<PedidoDTO> listar(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidoMapper.toCollectionModel(pedidos);
    }

    @GetMapping(value = "/{pedidoId}")
    public PedidoDTO buscarPorId(@PathVariable Long pedidoId){
        return pedidoMapper.toDTO(pedidoService.buscarOuFalhar(pedidoId));
    }
}
