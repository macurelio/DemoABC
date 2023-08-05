package com.example.consumo.service;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.Documento;

import java.util.List;

public interface DocumentoService {
    List<Documento> getAll();

    List<Documento> findAllByIdRecep (Long idDoc);
    void saveReceptorByIdRecep(Documento documento);

    Documento findByIdDoc(long idDoc);

    Documento getConsumoByIdCon(long idCon);

    void deleteReceptorByIdCon(long idDoc);
}
