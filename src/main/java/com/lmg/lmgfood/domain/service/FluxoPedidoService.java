package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.model.StatusPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class FluxoPedidoService {

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @Transactional
    public void confirmar(Long pedidoId) {
        Pedido pedido = emissaoPedidoService.buscarOuFalhar(pedidoId);

        if (!StatusPedido.CRIADO.equals(pedido.getStatus())) {
            throw new NegocioException(String.format("Status do pedido %s não pode ser alterado de %s para %s",
                    pedido.getId(), pedido.getStatus().getDescricao(), StatusPedido.CONFIRMADO.getDescricao()
            ));
        }

        pedido.setStatus(StatusPedido.CONFIRMADO);
        pedido.setDataConfirmacao(LocalDateTime.now());
    }
}
