package com.lmg.lmgfood.domain.service;

import com.lmg.lmgfood.domain.filter.VendaDiariaFilter;

public interface VendaReportService {

    byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset);
}
