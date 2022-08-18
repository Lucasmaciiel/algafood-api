package com.lmg.lmgfood.api.controller;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.lmg.lmgfood.api.mapper.PedidoFormMapper;
import com.lmg.lmgfood.api.mapper.PedidoMapper;
import com.lmg.lmgfood.api.mapper.PedidoResumoMapper;
import com.lmg.lmgfood.api.model.PedidoDTO;
import com.lmg.lmgfood.api.model.PedidoResumoDTO;
import com.lmg.lmgfood.api.model.form.PedidoForm;
import com.lmg.lmgfood.domain.exception.EntidadeNaoEncontradaException;
import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.model.Usuario;
import com.lmg.lmgfood.domain.repository.PedidoRepository;
import com.lmg.lmgfood.domain.service.EmissaoPedidoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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

    @Autowired
    private PedidoResumoMapper pedidoResumoMapper;

    @Autowired
    private PedidoFormMapper pedidoFormMapper;

    @Autowired
    private EmissaoPedidoService emissaoPedidoService;

    @GetMapping
    public MappingJacksonValue listar(@RequestParam(required = false) String campos){
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<PedidoResumoDTO> pedidosResumoDto = pedidoResumoMapper.toCollectionModel(pedidos);

        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosResumoDto);
        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());

        if (StringUtils.isNotBlank(campos)){
            filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
        }

        pedidosWrapper.setFilters(filterProvider);
        return pedidosWrapper;
    }

    @GetMapping(value = "/{codigoPedido}")
    public PedidoDTO buscarPorId(@PathVariable String codigoPedido){
        return pedidoMapper.toDTO(pedidoService.buscarOuFalhar(codigoPedido));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionar(@Valid @RequestBody PedidoForm pedidoForm){

        try {
            Pedido novoPedido = pedidoFormMapper.toDomainObject(pedidoForm);

            //TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);

            novoPedido = emissaoPedidoService.emitir(novoPedido);
            return pedidoMapper.toDTO(novoPedido);
        } catch (EntidadeNaoEncontradaException e) {
            throw new NegocioException(e.getMessage(), e);
        }

    }
}
