package com.lmg.lmgfood.api.mapper;

import com.lmg.lmgfood.api.model.ProdutoDTO;
import com.lmg.lmgfood.api.model.form.ProdutoForm;
import com.lmg.lmgfood.domain.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoMapper {

    @Autowired
    private ModelMapper modelMapper;

    public ProdutoDTO toDTO(Produto produto) {
        return modelMapper.map(produto, ProdutoDTO.class);
    }

    public Produto toDomainObject(ProdutoForm produtoForm) {
        return modelMapper.map(produtoForm, Produto.class);
    }

    public void copyToDomainObject(ProdutoForm produtoForm, Produto produto){
        modelMapper.map(produtoForm, produto);
    }

    public List<ProdutoDTO> toList(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
