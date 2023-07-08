package com.example.crudabctestdos.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
@Table (name = "totales")
public class Totales implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private Double mntNeto;
    private Double mntExe;
    private Double iva;
    private Double mntoTotal;
    private Double montoNf;
    private Double totalPeriodo;
    private Double saldoAnterior;
    private Double vlrPagar;

    // Constructor, getters y setters


}