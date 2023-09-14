package com.example.consumo.servicio;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.Emisor;
import com.example.consumo.domain.Valor;
import io.micrometer.common.lang.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ValorRepository extends JpaRepository<Valor, Long> {

    List<Valor> findAll();
    Valor findByEmisor_IdEmis(Long idEmis);
    Valor findTopByEmisorIdEmisOrderByIdValorDesc(Long idEmis);
    @NonNull
    Valor findByValidoDesdeLessThanOrValidoHastaGreaterThanEqual( LocalDate fechaDesde, LocalDate fechaHasta);

    List<Valor> findByValidoDesdeLessThanEqualAndValidoHastaGreaterThanEqual(LocalDate lecturaAnt1, LocalDate lecturaAct2);


}
