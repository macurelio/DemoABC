package com.example.consumo.dao;

import com.example.consumo.domain.Documento;
import com.example.consumo.service.DocumentoService;
import com.example.consumo.servicio.DetalleRepository;
import com.example.consumo.servicio.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;


    @Override
    public List<Documento> getAll() {
        return null;
    }

    @Override
    public List<Documento> findAllByIdRecep(Long idDoc) {
        return documentoRepository.findAll();
    }

    @Override
    public void saveReceptorByIdRecep(Documento documento) {

    }

    @Override
    public Documento findByIdDoc(long idDoc) {
        return null;
    }

    @Override
    public Documento getConsumoByIdCon(long idCon) {
        return null;
    }

    @Override
    public void deleteReceptorByIdCon(long idDoc) {
        documentoRepository.deleteById(idDoc);

    }
}
