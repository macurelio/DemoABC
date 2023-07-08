package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleRepository extends JpaRepository<Detalle, Long> {
    List<Detalle> findByIdDoc(Long id);
}
