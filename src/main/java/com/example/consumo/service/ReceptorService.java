package com.example.consumo.service;

import com.example.consumo.domain.Receptor;

import java.util.List;

public interface ReceptorService {

    List<Receptor> getAll();


    List<Receptor> findAllByIdRecep(Long idRecep);

    void saveReceptor(Receptor receptor);

    Receptor getReceptorByIdRecep(long idRecep);

    Receptor getReceptorByIdCon(long idCon);

    void deleteReceptorByIdRecep(long idRecep);
}
