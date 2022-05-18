package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.FormaPagamentoMapper;
import com.lmg.lmgfood.api.model.FormaPagamentoDTO;
import com.lmg.lmgfood.api.model.form.FormaPagamentoForm;
import com.lmg.lmgfood.domain.model.FormaPagamento;
import com.lmg.lmgfood.domain.repository.FormaPagamentoRepository;
import com.lmg.lmgfood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/formas-pagamento")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    @Autowired
    private CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @Autowired
    private FormaPagamentoMapper mapper;

    @GetMapping
    public List<FormaPagamento> buscarTodas() {
        return formaPagamentoRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public FormaPagamentoDTO adicionar(@Valid @RequestBody FormaPagamentoForm formaPagamentoForm) {
        var formaPagamento = mapper.toDomainObject(formaPagamentoForm);
        return mapper.toDTO(cadastroFormaPagamentoService.adicionar(formaPagamento));
    }


	@PutMapping(value = "/{formaPagamentoId}")
	@ResponseStatus(value = HttpStatus.OK)
	public FormaPagamentoDTO atualizar(@PathVariable Long formaPagamentoId,
                                       @RequestBody @Valid FormaPagamentoForm formaPagamentoForm) {

		var formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
		mapper.copyToDomainObject(formaPagamentoForm, formaPagamento);

		return mapper.toDTO(cadastroFormaPagamentoService.adicionar(formaPagamento));
	}


    @GetMapping("/{formaPagamentoId}")
    public FormaPagamento buscarPorId(@PathVariable Long formaPagamentoId) {
        return cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.remover(formaPagamentoId);
    }
}
