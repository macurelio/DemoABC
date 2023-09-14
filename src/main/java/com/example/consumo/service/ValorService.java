package com.example.consumo.service;

import com.example.consumo.domain.Emisor;
import com.example.consumo.domain.Valor;

import java.time.LocalDate;
import java.util.List;


public interface ValorService {

    Valor saveValor (Long idEmis, Valor valor);
    Valor getValorByIdEmis(Long idEmis);
    Valor getUltimoConsumoByEmisorId(Long idEmis);

    Valor obtenerValoresPorEmisorYFechas( LocalDate validoDesde, LocalDate validoHasta);

    List<Valor> obtenerValorPorPeriodo (LocalDate validoDesde, LocalDate validoHasta);

}
