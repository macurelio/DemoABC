package com.example.crudabctestdos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
@Table(name = "receptor")
public class Receptor implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDoc;
    private String rutReceptor;
    private String rznSocrecep;
    private String dirRecep;
    private String cmnaRecep;
    private String ciudadRecep;

    public Long getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Long idDoc) {
        this.idDoc = idDoc;
    }

    public String getRutReceptor() {
        return rutReceptor;
    }

    public void setRutReceptor(String rutReceptor) {
        this.rutReceptor = rutReceptor;
    }

    public String getRznSocrecep() {
        return rznSocrecep;
    }

    public void setRznSocrecep(String rznSocrecep) {
        this.rznSocrecep = rznSocrecep;
    }

    public String getDirRecep() {
        return dirRecep;
    }

    public void setDirRecep(String dirRecep) {
        this.dirRecep = dirRecep;
    }

    public String getCmnaRecep() {
        return cmnaRecep;
    }

    public void setCmnaRecep(String cmnaRecep) {
        this.cmnaRecep = cmnaRecep;
    }

    public String getCiudadRecep() {
        return ciudadRecep;
    }

    public void setCiudadRecep(String ciudadRecep) {
        this.ciudadRecep = ciudadRecep;
    }
}
