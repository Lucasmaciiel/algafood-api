package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.filter.VendaDiariaFilter;
import com.lmg.lmgfood.domain.model.dto.VendaDiaria;

import java.util.List;

public interface VendaQueryService {

    List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffSet);
}
