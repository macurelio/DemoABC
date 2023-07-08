package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Totales;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface TotalesRepository extends JpaRepository<Totales, Long> {
    List<Totales> findByIdDoc(Long id);
}
