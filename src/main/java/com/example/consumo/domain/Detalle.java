package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
@Entity
@Table(name = "consumo")
public class Detalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCon;

    private Double otrosCargos;
    private Double lecturaValor1;
    private Double lecturaValor2;

    @Temporal(TemporalType.DATE)
    private Date lecturaAnt1;

    @Temporal(TemporalType.DATE)
    private Date lecturaAct1;

    @Temporal(TemporalType.DATE)
    private Date lecturaAnt2;

    @Temporal(TemporalType.DATE)
    private Date lecturaAct2;

    public double getConsumoKwh() {
        return (getLecturaValor1() - getLecturaValor2());
    }

    public int getDiasTranscurridos1() {
        if (lecturaAnt1 != null && lecturaAct1 != null) {
            long diff = lecturaAct1.getTime() - lecturaAnt1.getTime() + TimeUnit.DAYS.toMillis(1);
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }

    public int getDiasTranscurridos2() {
        if (lecturaAnt2 != null && lecturaAct2 != null) {
            long diff = lecturaAct2.getTime() - lecturaAnt2.getTime() + TimeUnit.DAYS.toMillis(1);
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }

    public int getDiasTranscurridosPer1() {
        if (lecturaAnt1 != null && lecturaAct1 != null) {
            long diff = lecturaAct1.getTime() - lecturaAnt1.getTime();
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }

    public int getDiasTranscurridosPer2() {
        if (lecturaAnt2 != null && lecturaAct2 != null) {
            long diff = lecturaAct2.getTime() - lecturaAnt2.getTime();
            return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        }
        return 0;
    }

    public double getDias() {
        return (getDiasTranscurridos1() + getDiasTranscurridos2());
    }

    public double getPorcentaje1() {
        int diasTranscurridos = getDiasTranscurridos1();
        // Calcula el porcentaje considerando 30 días en un mes
        return (diasTranscurridos / getDias()) * 100.0;
    }

    public double getPorcentaje2() {
        int diasTranscurridos = getDiasTranscurridos2();
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

    public Double getTotalCargoElect() {
        return Math.ceil(getTotalTpoBase() + getTotalCargoEner() + getTotalCompra());

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

    public Double getSaldoAnt(){
        return getTotalconImpuesto() - getTotalPagar();
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


}
