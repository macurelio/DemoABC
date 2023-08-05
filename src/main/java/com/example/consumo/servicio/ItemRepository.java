package com.example.consumo.servicio;

import com.example.consumo.domain.Documento;
import com.example.consumo.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdItem(Long id);

}