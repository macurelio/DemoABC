package com.example.consumo.servicio;

import com.example.consumo.domain.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    List<Detalle> findByIdCon(Long id);

    Detalle getDetalleByIdCon(long idCon);

}
