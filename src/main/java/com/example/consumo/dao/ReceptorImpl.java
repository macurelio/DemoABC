package com.example.consumo.dao;

import com.example.consumo.domain.Emisor;
import com.example.consumo.domain.Receptor;
import com.example.consumo.service.ReceptorService;
import com.example.consumo.servicio.EmisorRepository;
import com.example.consumo.servicio.ReceptorRepository;
import jakarta.persistence.EntityNotFoundException;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptorImpl implements ReceptorService {

    @Autowired
    private ReceptorRepository receptorRepository;
    @Autowired
    private EmisorRepository emisorRepository;


    @Override
    public List<Receptor> getAll() {
        return receptorRepository.findAll();
    }

    @Override
    public List<Receptor> findAllByIdRecep(long idEmis) {
        return receptorRepository.findByEmisor_IdEmis(idEmis);
    }

    @Override
    public List<Receptor> findAllByIdEmis(long idEmis) {
        return receptorRepository.findByEmisor_IdEmis(idEmis);
    }


    @Override
    public Receptor saveReceptor(Long idEmis, Receptor receptor) {
        Emisor emisor = emisorRepository.findById(idEmis)
                .orElseThrow(() -> new EntityNotFoundException("Emisor not found with id: " + idEmis));
        receptor.setEmisor(emisor);
        return receptorRepository.save(receptor);
    }
    @Override
    public Receptor editReceptor( Receptor receptor) {
        return receptorRepository.save(receptor);
    }

    @Override
    public Receptor getReceptorByIdRecep(long idRecep) {
        Optional<Receptor> optional = receptorRepository.findById(idRecep);
        Receptor receptor = null;
        if (optional.isPresent()) {
            receptor = optional.get();
        } else {
            throw new RuntimeException(" Receptor not found for id :: " + idRecep);
        }
        return receptor;

    }


    @Override
    public Receptor getReceptorByIdCon(long idCon) {
        Optional<Receptor> optional = receptorRepository.findById(idCon);
        Receptor receptor = null;
        if (optional.isPresent()) {
            receptor = optional.get();
        } else {
            throw new RuntimeException(" Receptor not found for id :: " + idCon);
        }
        return receptor;

    }

    @Override
    public Receptor getReceptorByIdEmis(long idEmis) {
        Optional<Receptor> optional = receptorRepository.findById(idEmis);
        Receptor receptor = null;
        if (optional.isPresent()) {
            receptor = optional.get();
        } else {
            throw new RuntimeException(" Receptor not found for id :: " + idEmis);
        }
        return receptor;
    }


    @Override
    public void deleteReceptorByIdRecep(long id) {
        receptorRepository.deleteById(id);
    }


}
