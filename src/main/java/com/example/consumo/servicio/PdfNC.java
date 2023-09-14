package com.example.consumo.servicio;

import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.ReceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PdfNC {
    @Autowired
    private ConsumoSer consumoService;
    @Autowired
    private EmisorRepository emisorRepository;
    @Autowired
    private ReceptorService receptorImpl;
}

