package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Entity
@Data
@Table(name = "consumo")
public class Consumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCon;

    private Double otrosCargos;
    private Double lecturaValor1;
    private Double lecturaValor2;
    @Temporal(TemporalType.DATE)
    private LocalDate lecturaAnt1;
    @Temporal(TemporalType.DATE)
    private LocalDate lecturaAct2;

    private Long idRecep;

    @OneToMany(targetEntity = Consumo.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idCon", referencedColumnName = "idRecep")
    private List<Consumo> consumos;

    public double getConsumoKwh() {
        return (getLecturaValor1() - getLecturaValor2());
    }


    public String getUltimoDiaDelMes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return lecturaAnt1.withDayOfMonth(lecturaAnt1.lengthOfMonth()).format(formatter);
    }

    public long getDiasTranscurridos1() {
        LocalDate ultimoDiaMes = lecturaAnt1.withDayOfMonth(lecturaAnt1.lengthOfMonth());
        return ChronoUnit.DAYS.between(lecturaAnt1, ultimoDiaMes) + 1;
    }


    public String getUltimoDiaDelMes2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ultimoDiaMes = lecturaAnt1.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate ultimoDiaMesMasUnDia = ultimoDiaMes.plusDays(1);
        return ultimoDiaMesMasUnDia.format(formatter);
    }

    public long getDiasTranscurridos2() {
        LocalDate ultimoDiaMesMasUnDia = lecturaAct2.withDayOfMonth(lecturaAct2.lengthOfMonth());
        return ChronoUnit.DAYS.between(lecturaAct2, ultimoDiaMesMasUnDia) + 1;
    }


    public double getDias() {
        return (getDiasTranscurridos1() + getDiasTranscurridos2());
    }

    public double getPorcentaje1() {
        long diasTranscurridos = getDiasTranscurridos1();
        // Calcula el porcentaje considerando 30 días en un mes
        return (diasTranscurridos / getDias()) * 100.0;
    }

    public double getPorcentaje2() {
        long diasTranscurridos = getDiasTranscurridos2();
        // Calcula el porcentaje considerando 30 días en un mes
        return (diasTranscurridos / getDias()) * 100.0;
    }

    public Double getPorcentajePrecio1() {
        return (getPorcentaje1() * getConsumoKwh()) / 100;
    }

    public Double getPorcentajePrecio2() {
        return (getPorcentaje2() * getConsumoKwh()) / 100;
    }

    public Double getPorcentajeArriendo1() {
        return (getPorcentaje1() * 1) / 100;
    }

    public Double getPorcentajeArriendo2() {
        return (getPorcentaje2() * 1) / 100;
    }

    public Double getAdminServicioPer1() {
        return (getPorcentajeArriendo1() * 1112.94);
    }


    public Double getAdminServicioPer2() {
        return (getPorcentajeArriendo2() * 1112.94);
    }

    public Double getElectPer1() {
        return (getPorcentajePrecio1() * 20.34);
    }

    public Double getElectPer2() {
        return (getPorcentajePrecio2() * 20.34);
    }

    public Double getCargoPer1() {
        return (getPorcentajePrecio1() * 0.487);
    }

    public Double getCargoPer2() {
        return (getPorcentajePrecio2() * 0.487);
    }

    public Double geCompraPer1() {
        return (getPorcentajePrecio1() * 13.158);
    }

    public Double geCompraPer2() {
        return (getPorcentajePrecio2() * 13.158);
    }

    public Double geTpotBasePer1() {
        return (getPorcentajePrecio1() * 29.495);
    }

    public Double geTpotBasePer2() {
        return (getPorcentajePrecio2() * 29.495);
    }

    public Double getCargoEnerPer1() {
        return (getPorcentajePrecio1() * 74.293);
    }

    public Double getCargoEnerPer2() {
        return (getPorcentajePrecio2() * 74.293);
    }

    public Double getArriendoPer1() {
        return (getPorcentajeArriendo1() * 244.606);
    }

    public Double getArriendoPer2() {
        return (getPorcentajeArriendo2() * 246.428);
    }


    public Double getTotalAdmin() {
        return Math.floor((getAdminServicioPer1() + getAdminServicioPer2()) * 1.19);

    }

    public Double getTotalElect() {
        return Math.floor((getElectPer1() + getElectPer2()) * 1.19);

    }

    public Double getTotalCargo() {
        return Math.floor(getCargoPer1() + getCargoPer2());

    }

    public Double getTotalCompra() {
        return Math.ceil((geCompraPer1() + geCompraPer2()) * 1.19);

    }

    public Double getTotalTpoBase() {
        return Math.ceil((geTpotBasePer1() + geTpotBasePer2()) * 1.19);

    }

    public Double getTotalCargoEner() {
        return Math.ceil((getCargoEnerPer1() + getCargoEnerPer2()) * 1.19);

    }

    public Double getTotalArriendo() {
        return Math.ceil((getArriendoPer1() + getArriendoPer2()) * 1.19);

    }

    public Double getNeto() {
        return Math.ceil((getTotal() - getTotalCargo()));


    }

    public Double getTotalNeto() {
        return Math.floor((getNeto()) / 1.19);

    }

    public Double getTotal() {
        return Math.floor(getTotalAdmin() + getTotalElect() + getTotalCargo() + getTotalCompra() + getTotalTpoBase() + getTotalCargoEner() + getTotalArriendo());

    }


    public Double getImpuesto() {
        return Math.ceil((getTotalNeto()) * 0.19);
    }

    public Double getTotalconImpuesto() {
        return

                Math.floor(getImpuesto() + getTotalNeto() + getTotalCargo());


    }

    public Double getTotalPagar() {
        return

                Math.ceil(getTotal() + getOtrosCargos()) - ((getTotal() + getOtrosCargos()) % 100);


    }


}