package com.example.consumo.servicio;


import com.example.consumo.domain.Consumo;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.ReceptorService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Service
public class PdfService {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ConsumoSer consumoSer;
    @Autowired
    private ReceptorService receptorService;

    public byte[] generarInformePorIdCliente(Long id) throws FileNotFoundException, JRException, SQLException {
        Consumo consumo = consumoSer.getConsumoByIdRecep(id);
        // Cargar el archivo de diseño del informe (.jrxml)
        File file = ResourceUtils.getFile("classpath:jasperreports/enelBoleta.jrxml");

        // Compilar el archivo de diseño del informe
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());


        // Crear los parámetros del informe
        Map<String, Object> parameter = new HashMap<>();


        // Generar el informe utilizando los datos de la base de datos
        try (Connection connection = dataSource.getConnection()) {
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameter, connection);

            // Exportar el informe a formato PDF
            return JasperExportManager.exportReportToPdf(jasperPrint);
        }
    }


}

