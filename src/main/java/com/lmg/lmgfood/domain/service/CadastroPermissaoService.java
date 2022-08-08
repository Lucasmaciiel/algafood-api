package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.exception.PermissaoNaoEncontradaException;
import com.lmg.lmgfood.domain.model.Permissao;
import com.lmg.lmgfood.domain.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroPermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao buscarOuFalhar(Long permissaoId){
        return permissaoRepository.findById(permissaoId)
                .orElseThrow(() -> new PermissaoNaoEncontradaException(permissaoId));
    }
}
