package com.lmg.lmgfood.api.controller;

import com.lmg.lmgfood.api.mapper.FormaPagamentoMapper;
import com.lmg.lmgfood.api.model.FormaPagamentoDTO;
import com.lmg.lmgfood.api.model.form.FormaPagamentoForm;
import com.lmg.lmgfood.domain.model.FormaPagamento;
import com.lmg.lmgfood.domain.repository.FormaPagamentoRepository;
import com.lmg.lmgfood.domain.service.CadastroFormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import javax.servlet.Servlet;
import javax.validation.Valid;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
    public ResponseEntity<List<FormaPagamentoDTO>> buscarTodas(ServletWebRequest request) {
        ShallowEtagHeaderFilter.disableContentCaching(request.getRequest());

        //Não faz sentido implementar pra formas de pagamento, pois terá muito pouco beneficio
        String eTag = "0";
        OffsetDateTime ultimaAtualizacao = formaPagamentoRepository.findUltimaAtualizacao();
        if (ultimaAtualizacao != null){
            eTag = String.valueOf(ultimaAtualizacao.toEpochSecond());
        }
        if (request.checkNotModified(eTag)){
            return null;
        }

        List<FormaPagamentoDTO> formaPagamentoDTOS = mapper.toCollectionModel(formaPagamentoRepository.findAll());
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic())
                .eTag(eTag)
                .body(formaPagamentoDTOS);
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
    public ResponseEntity<FormaPagamentoDTO> buscarPorId(@PathVariable Long formaPagamentoId) {

        var formaPagamento = mapper.toDTO(cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId));
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                .body(formaPagamento);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Long formaPagamentoId) {
        cadastroFormaPagamentoService.remover(formaPagamentoId);
    }
}
