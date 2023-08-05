package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "item")

public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItem;
    private Double consumo;
    private Double adminServicio;
    private Double arriendo;
    private Double transElec;
    private Double saldoAnterior;
    private Double totalNeto;
    private Double iva;
    private Double totalPagar;


}
