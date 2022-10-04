package com.lmg.lmgfood.infra.repository.service.report;

import com.lmg.lmgfood.domain.filter.VendaDiariaFilter;
import com.lmg.lmgfood.domain.service.VendaReportService;
import org.springframework.stereotype.Service;

@Service
public class PdfVendaDiariaReportServiceImpl implements VendaReportService {

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        return new byte[0];
    }
}
