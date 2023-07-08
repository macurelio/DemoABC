package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Documento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentoRepository extends JpaRepository <Documento, Long>{
    List<Documento> findByIdDoc(Long id);
}
