package com.example.consumo.service;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.Receptor;

import java.util.List;

public interface ConsumoSer {
    List<Consumo> getAll();

    List<Consumo> findAllByIdRecep (Long idCon);
    void saveReceptorByIdRecep(Consumo consumo);

    Consumo findByIdCon(long idCon);

    Consumo getConsumoByIdRecep(long idRecep);

    void deleteReceptorByIdCon(long idCon);
}
