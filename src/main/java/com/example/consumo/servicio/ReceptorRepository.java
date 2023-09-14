package com.example.consumo.servicio;

import com.example.consumo.domain.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {

    List<Receptor> findByEmisor_IdEmis(Long idEmis);

}
