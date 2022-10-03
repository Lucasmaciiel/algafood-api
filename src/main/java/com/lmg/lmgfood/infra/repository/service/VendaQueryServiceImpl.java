package com.lmg.lmgfood.infra.repository.service;

import com.lmg.lmgfood.domain.filter.VendaDiariaFilter;
import com.lmg.lmgfood.domain.model.Pedido;
import com.lmg.lmgfood.domain.model.dto.VendaDiaria;
import com.lmg.lmgfood.domain.model.enums.StatusPedido;
import com.lmg.lmgfood.domain.service.VendaQueryService;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet) {
        var builder = entityManager.getCriteriaBuilder();
        var query = builder.createQuery(VendaDiaria.class);  // tipo que a consulta retorna
        var root = query.from(Pedido.class);

        var predicates = new ArrayList<Predicate>();
        var functionConvertTzDataCriacao = builder.function(
                "convert_tz", Date.class,
                root.get("dataCriacao"),
                builder.literal("+00:00"),
                builder.literal(timeOffSet));

        var functionDateDataCriacao = builder.function("date", LocalDate.class, functionConvertTzDataCriacao);

        var selection = builder.construct(VendaDiaria.class,
                functionDateDataCriacao,
                builder.count(root.get("id")),
                builder.sum(root.get("valorTotal")));

        if (Objects.nonNull(filtro.getRestauranteId())){
            predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
        }

        if (Objects.nonNull(filtro.getDataCriacaoInicio())){
            predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
        }

        if (Objects.nonNull(filtro.getDataCriacaoFim())){
            predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
        }

        predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));

        query.select(selection);
        query.groupBy(functionDateDataCriacao);

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }
}
