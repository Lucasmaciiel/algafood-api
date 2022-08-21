package com.lmg.lmgfood.infra.repository.specification;

import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.repository.filter.PedidoFilter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;

public class PedidoSpecs {

    public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
        return (root, query, builder) -> {

            //Resolvendo problema do N + 1 com o fech
            root.fetch("restaurante").fetch("cozinha");
            root.fetch("cliente");

            var predicates = new ArrayList<Predicate>();

            //adiciona predicates no arrayList
            if (filtro.getClienteId() != null){
                predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
            }

            if (filtro.getRestauranteId() != null){
                predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
            }

            if (filtro.getDataCriacaoInicio() != null){
                predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));//maior ou igual
            }

            if (filtro.getDataCriacaoFim() != null){
                predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));//maior ou igual
            }

            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
