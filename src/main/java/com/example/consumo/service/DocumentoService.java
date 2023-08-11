package com.example.consumo.service;

import com.example.consumo.domain.Documento;

import java.util.List;

public interface DocumentoService {
    List<Documento> getAll();

    void saveReceptorByIdDoc(Documento documento);

    Documento findByIdDoc(long idDoc);

    Documento findByIdRecep(long idRecep);

    Documento getConsumoByIdCon(long idCon);

    void deleteReceptorByIdCon(long idDoc);
}
