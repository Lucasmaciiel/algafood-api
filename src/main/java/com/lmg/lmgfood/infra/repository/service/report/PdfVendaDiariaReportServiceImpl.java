package com.lmg.lmgfood.infra.repository.service.report;

import com.lmg.lmgfood.domain.filter.VendaDiariaFilter;
import com.lmg.lmgfood.domain.model.dto.VendaDiaria;
import com.lmg.lmgfood.domain.service.VendaQueryService;
import com.lmg.lmgfood.domain.service.VendaReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class PdfVendaDiariaReportServiceImpl implements VendaReportService {

    @Autowired
    private VendaQueryService vendaQueryService;

    @Override
    public byte[] emitirVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
        try {
            var inputStream = this.getClass().getResourceAsStream("/relatorios/vendas-diarias.jasper");

            var parametros = new HashMap<String, Object>();
            parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

            List<VendaDiaria> vendaDiarias = vendaQueryService.consultarVendasDiarias(filtro, timeOffset);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(vendaDiarias);

            JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
            return JasperExportManager.exportReportToPdf(jasperPrint);
        } catch (Exception e) {
            throw new ReportException("Não foi possível gerar o relatório de vendas diárias", e);
        }
    }
}
