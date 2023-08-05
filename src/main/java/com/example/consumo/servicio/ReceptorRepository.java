package com.example.consumo.servicio;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {

    List<Receptor> findByIdRecep(Long idRecep);
}
