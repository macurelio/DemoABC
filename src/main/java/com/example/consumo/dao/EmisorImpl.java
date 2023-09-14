package com.example.consumo.dao;

import com.example.consumo.domain.Emisor;
import com.example.consumo.service.EmisorService;
import com.example.consumo.servicio.EmisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmisorImpl implements EmisorService {
    @Autowired
    private EmisorRepository emisorRepository;


    @Override
    public Emisor getEmisorByIdEmis(long idEmis) {
      return emisorRepository.findByIdEmis(idEmis);

    }

    @Override
    public Emisor getEmisorByIdRecep(long idRecep) {
        Optional<Emisor> optional = emisorRepository.findById(idRecep);
        Emisor emisor = null;
        if (optional.isPresent()) {
            emisor = optional.get();
        } else {
            throw new RuntimeException(" Emisor not found for id :: " + idRecep);
        }
        return emisor;

    }




    @Override
    public List<Emisor> getAllEmisors() {
        return emisorRepository.findAll();
    }

    @Override
    public void guardarEmisor(Emisor emisor) {
this.emisorRepository.save(emisor);
    }

    @Override
    public void eliminaEmisor(Long idEmis) {
emisorRepository.deleteById(idEmis);
    }
}
