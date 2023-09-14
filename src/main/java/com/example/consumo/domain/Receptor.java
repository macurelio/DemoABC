package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "receptor")
public class Receptor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRecep;

    private String rutReceptor;
    private String rznSocrecep;
    private String giroEmis;
    private String dirRecep;
    private String cmnaRecep;
    private String ciudadRecep;

    @ManyToOne
    @JoinColumn(name = "idEmis")
    private Emisor emisor;
}


