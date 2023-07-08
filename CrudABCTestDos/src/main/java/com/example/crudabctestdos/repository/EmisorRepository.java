package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Emisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmisorRepository extends JpaRepository<Emisor, Long> {
    List<Emisor> findByIdDoc(Long id);
}
