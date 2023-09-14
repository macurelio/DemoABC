package com.example.consumo.dao;

import com.example.consumo.domain.Emisor;
import com.example.consumo.domain.Valor;
import com.example.consumo.service.ValorService;
import com.example.consumo.servicio.EmisorRepository;
import com.example.consumo.servicio.ValorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ValorImpl implements ValorService {
    @Autowired
    private EmisorRepository emisorRepository;
    @Autowired
    private ValorRepository valorRepository;


    public Valor saveValor(Long idEmis, Valor valor) {
        Emisor emisor = emisorRepository.findById(idEmis)
                .orElseThrow(() -> new EntityNotFoundException("Emisor not found with id: " + idEmis));
        valor.setEmisor(emisor);
        return valorRepository.save(valor);
    }

    public Valor obtenerValoresPorEmisorYFechas( LocalDate validoDesde, LocalDate validoHasta) {
        return valorRepository.findByValidoDesdeLessThanOrValidoHastaGreaterThanEqual( validoDesde, validoHasta);
    }

    @Override
    public List<Valor> obtenerValorPorPeriodo(LocalDate validoDesde, LocalDate validoHasta) {
        return valorRepository.findByValidoDesdeLessThanEqualAndValidoHastaGreaterThanEqual(validoDesde, validoHasta);
    }


    @Override
    public Valor getValorByIdEmis(Long idEmis) {
        return valorRepository.findByEmisor_IdEmis(idEmis);
    }

    @Override
    public Valor getUltimoConsumoByEmisorId(Long idEmis) {
        return valorRepository.findTopByEmisorIdEmisOrderByIdValorDesc(idEmis);
    }


}
