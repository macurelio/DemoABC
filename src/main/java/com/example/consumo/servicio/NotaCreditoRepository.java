package com.example.consumo.servicio;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.NotaCredito;
import com.example.consumo.domain.Valor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotaCreditoRepository extends JpaRepository<NotaCredito, Long> {

    NotaCredito findByConsumo_Folio(Long folio);
}
