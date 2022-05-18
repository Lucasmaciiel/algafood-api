package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.FormaPagamentoDTO;
import com.lmg.lmgfood.api.model.form.FormaPagamentoForm;
import com.lmg.lmgfood.domain.model.FormaPagamento;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FormaPagamentoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public FormaPagamentoDTO toDTO(FormaPagamento formaPagamento) {
        return modelMapper.map(formaPagamento, FormaPagamentoDTO.class);
    }


    public FormaPagamento toDomainObject(FormaPagamentoForm formaPagamentoForm) {
        return modelMapper.map(formaPagamentoForm, FormaPagamento.class);
    }

    public List<FormaPagamentoDTO> toList(List<FormaPagamento> formaPagamentos){
        return formaPagamentos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Copia os dados para o objeto de dominio
     * @param formaPagamentoForm objeto de onde será copiado
     * @param formaPagamento objeto destino
     */
    public void copyToDomainObject(FormaPagamentoForm formaPagamentoForm, FormaPagamento formaPagamento) {
        modelMapper.map(formaPagamentoForm, formaPagamento);
    }
}
