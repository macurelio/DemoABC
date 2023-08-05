package com.example.consumo.servicio;

import com.example.consumo.domain.Totales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TotalesRepository extends JpaRepository<Totales, Long> {
    List<Totales> findByIdDoc(Long id);

}