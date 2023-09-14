package com.example.consumo.service;

import com.example.consumo.domain.Emisor;

import java.util.List;
import java.util.Optional;

public interface EmisorService {
    Emisor getEmisorByIdEmis(long idEmis);
    Emisor getEmisorByIdRecep(long idRecep);
    List<Emisor> getAllEmisors();
    void guardarEmisor(Emisor emisor);
    void eliminaEmisor(Long idEmis);

}
