package com.example.crudabctestdos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "item")

public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private Double consumo;
    private Double adminServicio;
    private Double arriendo;
    private Double transElec;
    private Double saldoAnterior;


    public Double getTotalBoleta(){
        return (adminServicio + transElec + consumo + arriendo);
    }
    public Double getExcento(){
        return Math.ceil((getTotalBoleta() * 0.0026));
    }
    public Double getNeto() {
        return  Math.ceil((getTotalBoleta() - getExcento()));
    }

    public Double getTotal(){
        return Math.ceil((getNeto() ) / 1.19);

    }
    public Double getImpuesto() {
        return Math.ceil((getTotal() )*0.19) ;
    }

    public Double getTotalconImpuesto(){
        return

        Math.ceil(getImpuesto() + getTotal() + getSaldoAnterior()) - ((getImpuesto() + getTotal() + getSaldoAnterior() )% 100);

    }

    public Double getAjusteActual() {
        return (getTotalconImpuesto() - getTotal()) ;
    }


}
