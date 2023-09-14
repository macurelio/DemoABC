package com.example.consumo.DTE;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.Receptor;

import java.util.Date;

public class NotaCredito {
    private Receptor receptor;
    private Consumo consumo;

    public Long getIdRecepNC() {
        return receptor.getIdRecep();
    }

    public String getRutNC() {
        return receptor.getRutReceptor();
    }

    public String getRznSocrecepNC() {
        return receptor.getRznSocrecep();
    }

    public String getDirRecepNC() {
        return receptor.getDirRecep();
    }

    public String getCmnaRecepNC() {
        return receptor.getCmnaRecep();
    }

    public String getCiudadRecepNC() {
        return receptor.getCiudadRecep();
    }




   /* public String getIndServicioNC() {
        return consumo.getIndServicio();
    }

    */


    public Long getIdConNC() {
        return consumo.getFolio();
    }


}
