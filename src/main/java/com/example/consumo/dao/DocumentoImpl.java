package com.example.consumo.dao;

import com.example.consumo.domain.Documento;
import com.example.consumo.service.DocumentoService;
import com.example.consumo.servicio.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentoImpl implements DocumentoService {

    @Autowired
    private DocumentoRepository documentoRepository;


    @Override
    public List<Documento> getAll() {
        return documentoRepository.findAll();
    }


    @Override
    public void saveReceptorByIdDoc(Documento documento) {
        this.documentoRepository.save(documento);
    }

    @Override
    public Documento findByIdDoc(long idDoc) {
        Optional<Documento> optional = documentoRepository.findById(idDoc);
        Documento documento = null;
        if (optional.isPresent()) {
            documento = optional.get();
        } else {
            throw new RuntimeException(" Documento not found for id :: " + idDoc);
        }
        return documento;

    }

    @Override
    public Documento findByIdRecep(long idRecep) {
        Optional<Documento> optional = documentoRepository.findById(idRecep);
        Documento documento = null;
        if (optional.isPresent()) {
            documento = optional.get();
        } else {
            throw new RuntimeException(" Documento not found for id :: " + idRecep);
        }
        return documento;

    }

    @Override
    public Documento getConsumoByIdCon(long idCon) {
        Optional<Documento> optional = documentoRepository.findById(idCon);
        Documento documento = null;
        if (optional.isPresent()) {
            documento = optional.get();
        } else {
            throw new RuntimeException(" Documento not found for id :: " + idCon);
        }
        return documento;

    }

    @Override
    public void deleteReceptorByIdCon(long id) {
        documentoRepository.deleteById(id);

    }
}
