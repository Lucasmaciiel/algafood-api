package com.lmg.lmgfood.api.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
public class FormaPagamentoDTO {

    private Long id;
    private String descricao;
}
