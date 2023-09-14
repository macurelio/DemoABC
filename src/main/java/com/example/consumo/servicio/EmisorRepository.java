package com.example.consumo.servicio;

import com.example.consumo.domain.Emisor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface EmisorRepository extends JpaRepository<Emisor, Long> {
    Emisor findByIdEmis(Long idEmis);


}
