package com.example.crudabctestdos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "documento")
public class Documento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private String tipoDte;
    private String folio;
    private String fchEmis;
    private String indServicio;
    private String fchVenc;
}
