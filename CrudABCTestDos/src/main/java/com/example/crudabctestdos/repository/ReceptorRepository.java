package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Receptor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
public interface ReceptorRepository extends JpaRepository<Receptor, Long> {
    List<Receptor> findByIdDoc(Long id);
}
