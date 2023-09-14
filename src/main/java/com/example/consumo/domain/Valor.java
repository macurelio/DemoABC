package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Data
@Entity
@Table(name = "valor")
public class Valor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        private Long idValor;
    private Double prcAdminservicio;
    private Double prcElec;
    private Double prcCargo;
    private Double prcCompra;
    private Double prcTpobase;
    private Double prcCargoener;
    private Double prcArriendo;
    @Temporal(TemporalType.DATE)
    private LocalDate validoDesde;
    @Temporal(TemporalType.DATE)
    private LocalDate validoHasta;

    @ManyToOne
    @JoinColumn(name = "idEmis")
    private Emisor emisor;

    @PrePersist
    protected void setValidoHasta() {
        this.validoHasta = validoDesde.withDayOfMonth(validoDesde.lengthOfMonth());
    }
}
