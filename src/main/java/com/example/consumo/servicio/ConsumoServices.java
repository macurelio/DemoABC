package com.example.consumo.servicio;

import com.example.consumo.domain.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoServices extends JpaRepository<Consumo, Long> {

    List<Consumo> findAllByIdRecep(Long idRecep);


}
