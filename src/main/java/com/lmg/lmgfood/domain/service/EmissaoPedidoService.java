package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.exception.PedidoNaoEncontradoException;
import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmissaoPedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido buscarOuFalhar(Long pedidoId){
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new PedidoNaoEncontradoException(pedidoId));
    }
}
