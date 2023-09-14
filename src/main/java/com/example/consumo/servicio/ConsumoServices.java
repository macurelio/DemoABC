package com.example.consumo.servicio;

import com.example.consumo.domain.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ConsumoServices extends JpaRepository<Consumo, Long> {


    List<Consumo> findByReceptor_IdRecep(Long idRecep);
    List<Consumo> findByEmisor_IdEmis(Long idEmis);
    Consumo findTopByReceptorIdRecepOrderByFolioDesc(Long idRecep);
    Consumo findTopByEmisorIdEmisOrderByFolioDesc(Long idEmis);




}
