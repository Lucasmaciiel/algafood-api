package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.ProdutoMapper;
import com.lmg.lmgfood.api.model.ProdutoDTO;
import com.lmg.lmgfood.api.model.form.ProdutoForm;
import com.lmg.lmgfood.domain.model.Produto;
import com.lmg.lmgfood.domain.model.Restaurante;
import com.lmg.lmgfood.domain.repository.ProdutoRepository;
import com.lmg.lmgfood.domain.service.CadastroProdutoService;
import com.lmg.lmgfood.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos")
public class RestauranteProdutoController {

    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;

    @Autowired
    private CadastroProdutoService cadastroProdutoService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoMapper mapper;

    @GetMapping
    public List<ProdutoDTO> listar(@PathVariable Long restauranteId){
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        List<Produto> todosProdutos = produtoRepository.findByRestaurante(restaurante);
        return this.mapper.toList(todosProdutos);
    }

    @GetMapping("/{produtoId}")
    public ProdutoDTO buscarPorId(@PathVariable Long restauranteId,
                                  @PathVariable Long produtoId){

        Produto produto = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);
        return mapper.toDTO(produto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoDTO adicionar(@PathVariable Long restauranteId,
                                @RequestBody ProdutoForm produtoForm){
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
        Produto produto = mapper.toDomainObject(produtoForm);
        produto.setRestaurante(restaurante);

        cadastroProdutoService.salvar(produto);

        return mapper.toDTO(produto);

    }

    @PutMapping("/{produtoId}")
    public ProdutoDTO atualizar(@PathVariable Long restauranteId,
                                @PathVariable Long produtoId,
                                @RequestBody ProdutoForm produtoForm) {
        Produto produtoAtual = cadastroProdutoService.buscarOuFalhar(restauranteId, produtoId);

        mapper.copyToDomainObject(produtoForm, produtoAtual);

        produtoAtual = cadastroProdutoService.salvar(produtoAtual);

        return mapper.toDTO(produtoAtual);
    }
}
