package com.example.crudabctestdos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "item")
public class Detalle implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private Double consumo;
    private Double adminServicio;
    private Double arriendo;
    private Double transElec;
    private Double saldoAnterior;

public String getConsumoNombre(){ return "Consumo";}
    public String getAdminServicioNombre(){ return "AdminServicio";}
    public String getArriendoNombre(){ return "Arriendo del medidor";}
    public String geTransElecNombre(){ return "Transporte de Electricidad";}
    public String getSaldoAnteriorNombre(){ return "Saldo anterior";}

    public String getAllQtyItem(){ return "1.000000" ;}

    //public String getConsumoQtyItem(){ return "1.000000" ;}

    //public String getAdminServicioQtyItem(){ return "1.000000" ;}

    //public String getArriendoQtyItem(){ return "1.000000" ;}

    //public String getTransElecQtyItem(){ return "1.000000" ;}

    //public String getSaldoAnteriorQtyItem(){ return "1.000000" ;}



    public String getTpoCodigo(){ return "INT";}

    public String getAdminServCodigo(){ return "11000060" ;}
    public String getTransElecCodigo(){ return "1000033" ;}

    public String getTransElecExceCodigo(){ return "1000114" ;}

    public String getElecConsumoCodigo(){ return "1000100" ;}

    public String getArriendoCodigo(){ return "1000050" ;}

    public String getAjusteCodigo(){ return "650010" ;}

    public String getUnmdItem1(){ return "Ea";}

    public String getUnmdItem2(){ return "KWH";}


}
