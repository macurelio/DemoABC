package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@Table(name = "consumo")
public class Consumo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folio;
    private Double otrosCargos;
    private Double lecturaValor1;
    private Double lecturaValor2;
    @Temporal(TemporalType.DATE)
    private LocalDate lecturaAnt1;
    @Temporal(TemporalType.DATE)
    private LocalDate lecturaAct2;
    private String tipoDte;
    @Temporal(TemporalType.DATE)
    private LocalDate fchEmis;
    @Temporal(TemporalType.DATE)
    private LocalDate fchVenc;
    private Double periodo1;
    private Double periodo2;
    private Double consumoKwh;
    private Double adminServicio;
    private Double transElect;
    private Double consumoTotal;
    private Double arriendo;
    private Double compra;
    private Double tpobase;
    private Double cargoEner;

    private Double adminServicio2;
    private Double transElect2;
    private Double consumoTotal2;
    private Double arriendo2;
    private Double compra2;
    private Double cargoEner2;
    private Double tpobase2;

    private Double totalAdmin;
    private Double totalTrans;
    private Double totalCompra;
    private Double totalTpo;
    private Double totalArriendo;
    private Double totalCargo;
    private Double totalConsumo;
    private Double ajusteCargo;
    private Double neto;
    private Double iva;
    private Double total;
    private Double mntoEx;

public Double getImpuesto (){
    return 0.19;
}

    @ManyToOne
    @JoinColumn(name = "idEmis")
    private Emisor emisor;


    @ManyToOne
    @JoinColumn(name = "idRecep")
    private Receptor receptor;
    @PrePersist
    protected void onCreate() {
        fchEmis = LocalDate.now();
        fchVenc = fchEmis.plusDays(15); // Agregar 15 días a la fecha de emisión
    }
    public LocalDate getFechaEmision() {
        return fchEmis;
    }
    public LocalDate getFechaVencimiento() {
        return fchVenc;
    }

    public double getConsumoKwh() {
        return (getLecturaValor2() - getLecturaValor1());
    }


    public String getUltimoDiaDelMes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return lecturaAnt1.withDayOfMonth(lecturaAnt1.lengthOfMonth()).format(formatter);
    }

    public double getDiasTranscurridos1() {
        LocalDate ultimoDiaMes = lecturaAnt1.withDayOfMonth(lecturaAnt1.lengthOfMonth());
        return ChronoUnit.DAYS.between(lecturaAnt1, ultimoDiaMes) + 1;
    }


    public String getUltimoDiaDelMes2() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate ultimoDiaMes = lecturaAnt1.with(TemporalAdjusters.lastDayOfMonth());
        LocalDate ultimoDiaMesMasUnDia = ultimoDiaMes.plusDays(1);
        return ultimoDiaMesMasUnDia.format(formatter);
    }

    public double getDiasTranscurridos2() {
        LocalDate ultimoDiaMesMasUnDia = lecturaAct2.withDayOfMonth(lecturaAct2.lengthOfMonth());
        return ChronoUnit.DAYS.between(lecturaAct2, ultimoDiaMesMasUnDia) + 1;
    }

    public double getDias(){
        return getDiasTranscurridos1() + getDiasTranscurridos2();
    }

    public String getConsumoNombre() {
        return "Consumo";
    }
    public String getAdminServicioNombre() {
        return "AdminServicio";
    }
    public String getArriendoNombre() {
        return "Arriendo del medidor";
    }
    public String geTransElecNombre() {
        return "Transporte de Electricidad";
    }
    public String getSaldoAnteriorNombre() {
        return "Saldo anterior";
    }
    public String getAjusteAnt() {
        return "Ajuste para facilitar el pago en efectivo, mes anterior";
    }
    public String getAjusteAct() {
        return "Ajuste para facilitar el pago en efectivo, mes actual";
    }
    public String getAllQtyItem() {
        return "1.000000";
    }

    //public String getConsumoQtyItem(){ return "1.000000" ;}

    //public String getAdminServicioQtyItem(){ return "1.000000" ;}

    //public String getArriendoQtyItem(){ return "1.000000" ;}

    //public String getTransElecQtyItem(){ return "1.000000" ;}

    //public String getSaldoAnteriorQtyItem(){ return "1.000000" ;}
    public String getTpoCodigo() {
        return "INT";
    }
    public String getAdminServCodigo() {
        return "11000060";
    }
    public String getTransElecCodigo() {
        return "1000033";
    }
    public String getTransElecExceCodigo() {
        return "1000114";
    }
    public String getElecConsumoCodigo() {
        return "1000100";
    }
    public String getArriendoCodigo() {
        return "1000050";
    }
    public String getAjusteCodigo() {
        return "650010";
    }
    public String getUnmdItem1() {
        return "Ea";
    }
    public String getUnmdItem2() {
        return "KWH";
    }




    public void calcularMontos() {
        this.fchEmis = getFechaEmision();
        this.fchVenc = getFechaVencimiento();
        this.consumoKwh = getConsumoKwh();
    }


}