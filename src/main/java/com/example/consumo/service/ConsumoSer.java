package com.example.consumo.service;

import com.example.consumo.domain.Consumo;

import java.time.LocalDate;
import java.util.List;

public interface ConsumoSer {
    Consumo getConsumoById(Long id);

    List<Consumo> getAllConsumos();

    List<Consumo> getConsumosByIdRecep(Long idRecep);

    List<Consumo> getConsumosByIdEmis(Long idEmis);

    Consumo getUltimoConsumoByReceptorId(Long idRecep);

    Consumo getUltimoConsumoByEmisorId(Long idEmis);

    Consumo guardarConsumo(Long idRecep, Long idEmis, Consumo consumo);

    void eliminarConsumo(Long idCon);

    Consumo calcularValores(Long idEmis, Consumo consumo);

}
