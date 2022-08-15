package com.lmg.lmgfood.api.model;

import com.lmg.lmgfood.domain.model.enums.StatusPedido;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class PedidoResumoDTO {

    private String codigo;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private OffsetDateTime dataCriacao;
    private RestauranteResumoDTO restaurante;
    private UsuarioDTO cliente;
    private StatusPedido status;

}
