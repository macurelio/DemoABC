package com.example.consumo.dao;

import com.example.consumo.domain.Receptor;
import com.example.consumo.service.ReceptorService;
import com.example.consumo.servicio.ReceptorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptorImpl implements ReceptorService {

    @Autowired
    private ReceptorRepository receptorRepository;


    @Override
    public List<Receptor> getAll() {
        return receptorRepository.findAll();
    }

    @Override
    public List<Receptor> findAllByIdRecep(Long idRecep) {
        return receptorRepository.findByIdRecep(idRecep);
    }


    @Override
    public void saveReceptor(Receptor receptor) {
        this.receptorRepository.save(receptor);
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
    public void deleteReceptorByIdRecep(long id) {
        receptorRepository.deleteById(id);
    }


}
