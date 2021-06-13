package com.lmg.lmgfood.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;

	@JoinColumn(nullable = false)
	private String nome;

	@JoinColumn(nullable = false)
	private String descricao;

	@JoinColumn(nullable = false)
	private BigDecimal preco;

	@JoinColumn(nullable = false)
	private boolean ativo;

	@JoinColumn(nullable = false)
	@ManyToOne
	private Restaurante restaurante;
}
