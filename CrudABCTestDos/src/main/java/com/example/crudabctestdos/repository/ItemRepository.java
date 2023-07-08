package com.example.crudabctestdos.repository;

import com.example.crudabctestdos.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByIdDoc(Long id);

}
