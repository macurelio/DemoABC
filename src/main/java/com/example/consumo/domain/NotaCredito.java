package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

@Entity
@Data
@Table(name = "notaCredito")
public class NotaCredito implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long folioNC;
    private Double mntoAfecto;
    private Double mntoNoafecto;
    private Double iva;
    private Double total;
    private Double subAfecto;
    private Double subNoafecto;
    private Double adminServicio;
    private Double transElect;
    private Double consumoTotal;
    private Double arriendo;
    private Double ajusteCargo;

    @ManyToOne
    @JoinColumn(name = "folio")
    private Consumo consumo;


}
