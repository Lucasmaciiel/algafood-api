package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.exception.ProdutoNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Produto;
import com.lmg.lmgfood.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CadastroProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }

    public Produto buscarOuFalhar(Long restauranteId, Long produtoId){
        return produtoRepository.findById(restauranteId, produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradaException(restauranteId, produtoId));
    }


}
