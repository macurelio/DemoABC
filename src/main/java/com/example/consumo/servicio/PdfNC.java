package com.example.consumo.servicio;

import com.example.consumo.domain.*;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.DocumentoService;
import com.example.consumo.service.ReceptorService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@Service
public class PdfNC {
    @Autowired
    private ConsumoSer consumoService;
    @Autowired
    private EmisorRepository emisorRepository;
    @Autowired
    private ReceptorService receptorImpl;
    @Autowired
    private DocumentoService documentoService;
    @Autowired
    private DetalleRepository detalleRepository;
}

