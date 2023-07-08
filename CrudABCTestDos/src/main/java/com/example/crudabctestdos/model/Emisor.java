package com.example.crudabctestdos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

    @Data
    @Entity
    @Table(name = "emisor")
    public class Emisor implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long idDoc;
        private String rutEmisor;
        private String rzonSociemisor;
        private String giroEmisor;
        private String dirOrigen;
        private String cmnaEmisor;
        private String ciudadEmisor;

        public Long getIdDoc() {
            return idDoc;
        }

        public void setIdDoc(Long idDoc) {
            this.idDoc = idDoc;
        }

        public String getRutEmisor() {
            return rutEmisor;
        }

        public void setRutEmisor(String rutEmisor) {
            this.rutEmisor = rutEmisor;
        }

        public String getRzonSociemisor() {
            return rzonSociemisor;
        }

        public void setRzonSociemisor(String rzonSociemisor) {
            this.rzonSociemisor = rzonSociemisor;
        }

        public String getGiroEmisor() {
            return giroEmisor;
        }

        public void setGiroEmisor(String giroEmisor) {
            this.giroEmisor = giroEmisor;
        }

        public String getDirOrigen() {
            return dirOrigen;
        }

        public void setDirOrigen(String dirOrigen) {
            this.dirOrigen = dirOrigen;
        }

        public String getCmnaEmisor() {
            return cmnaEmisor;
        }

        public void setCmnaEmisor(String cmnaEmisor) {
            this.cmnaEmisor = cmnaEmisor;
        }

        public String getCiudadEmisor() {
            return ciudadEmisor;
        }

        public void setCiudadEmisor(String ciudadEmisor) {
            this.ciudadEmisor = ciudadEmisor;
        }
    }


