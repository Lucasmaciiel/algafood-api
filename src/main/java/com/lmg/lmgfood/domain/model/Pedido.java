package com.lmg.lmgfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import com.lmg.lmgfood.domain.exception.NegocioException;
import com.lmg.lmgfood.domain.model.enums.StatusPedido;
import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	private String codigo;

	private BigDecimal subtotal;

	private BigDecimal taxaFrete;

	private BigDecimal valorTotal;

	@CreationTimestamp
	private OffsetDateTime dataCriacao;

	private LocalDateTime dataConfirmacao;
	
	private LocalDateTime dataCancelamento;
	
	private LocalDateTime dataEntrega;

	@JoinColumn(nullable = false)
	@ManyToOne(fetch = FetchType.LAZY)  // Realiza o select apenas quando precisar
	private FormaPagamento formaPagamento;

	@ManyToOne
	@JoinColumn(nullable = false)
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "usuario_cliente_id", nullable = false)
	private Usuario cliente;

	@Embedded
	private Endereco enderecoEntrega;

	@Enumerated(EnumType.STRING)
	private StatusPedido status = StatusPedido.CRIADO;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<ItemPedido> itens = new ArrayList<>();

	public void calcularValorTotal(){
		getItens().forEach(ItemPedido::calcularPrecoTotal);

		this.subtotal = getItens().stream()
				.map(ItemPedido::getPrecoTotal)
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void confirmar(){
		setStatus(StatusPedido.CONFIRMADO);
		setDataConfirmacao(LocalDateTime.now());
	}

	public void cancelar(){
		setStatus(StatusPedido.CANCELADO);
		setDataCancelamento(LocalDateTime.now());
	}

	public void entregar(){
		setStatus(StatusPedido.ENTREGUE);
		setDataEntrega(LocalDateTime.now());
	}

	private void setStatus(StatusPedido novoStatus){
		if (getStatus().naoPodeAlterarPara(novoStatus)){
			throw new NegocioException((String.format("Status do pedido %s não pode ser alterado de %s para %s",
					getCodigo(), getStatus().getDescricao(), novoStatus.getDescricao())));
		}

		this.status = novoStatus;
	}

	@PrePersist //Anotation do jpa, Antes de inserir um novo registro no banco de dados, executa esse método, método de callback
	private void gerarCodigoUUID(){
		setCodigo(UUID.randomUUID().toString());
	}

}





