package com.example.consumo.dao;

import com.example.consumo.domain.Consumo;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.servicio.ConsumoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsumoImpl implements ConsumoSer {

    @Autowired
    private ConsumoServices consumoServices;


    @Override
    public List<Consumo> getAll() {
        return consumoServices.findAll();
    }

    @Override
    public List<Consumo> findAllByIdRecep(Long idRecep) {
        return consumoServices.findAllByIdRecep(idRecep);
    }

    @Override
    public void saveReceptorByIdRecep(Consumo consumo) {
        this.consumoServices.save(consumo);

    }

    @Override
    public Consumo findByIdCon(long idCon) {
        Optional<Consumo> optional = consumoServices.findById(idCon);
        Consumo consumo = null;
        if (optional.isPresent()) {
            consumo = optional.get();
        } else {
            throw new RuntimeException(" Consumo not found for id :: " + idCon);
        }
        return consumo;

    }


    @Override
    public Consumo getConsumoByIdRecep(long idRecep) {
        Optional<Consumo> optional = consumoServices.findById(idRecep);
        Consumo receptor = null;
        if (optional.isPresent()) {
            receptor = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + idRecep);
        }
        return receptor;
    }

    @Override
    public void deleteReceptorByIdCon(long id) {
        consumoServices.deleteById(id);

    }
}

