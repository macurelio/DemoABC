package com.example.consumo.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

    @Data
    @Entity
    @Table(name = "emisor")
    public class Emisor implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idEmis;
        private String rutEmisor;
        private String rzonSociemisor;
        private String giroEmisor;
        private String dirOrigen;
        private String cmnaEmisor;
        private String ciudadEmisor;


    }


