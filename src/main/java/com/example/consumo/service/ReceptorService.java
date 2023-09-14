package com.example.consumo.service;

import com.example.consumo.domain.Receptor;

import java.util.List;

public interface ReceptorService {

    List<Receptor> getAll();
    List<Receptor> findAllByIdRecep(long idRecep);
    List<Receptor> findAllByIdEmis(long idRecep);
    Receptor saveReceptor(Long idEmis, Receptor receptor);
    Receptor editReceptor( Receptor receptor);
    Receptor getReceptorByIdRecep(long idRecep);
    Receptor getReceptorByIdCon(long idCon);
    Receptor getReceptorByIdEmis(long idEmis);
    void deleteReceptorByIdRecep(long idRecep);
}
