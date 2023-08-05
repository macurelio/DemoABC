package com.example.consumo.DTE;

import com.example.consumo.domain.Documento;
import com.example.consumo.domain.Receptor;

public class NotaCredito {
    private Receptor receptor;
    private Documento documento;

    public Long getIdRecepNC(){
        return receptor.getIdRecep();
    }
    public String getRutNC(){
        return receptor.getRutReceptor();
    }
    public String getRznSocrecepNC(){
        return receptor.getRznSocrecep();
    }
    public String getDirRecepNC(){
        return receptor.getDirRecep();
    }

    public String getCmnaRecepNC(){
        return receptor.getCmnaRecep();
    }
    public String getCiudadRecepNC(){
        return receptor.getCiudadRecep();
    }

    public Long getIdDocNC(){
        return documento.getIdDoc();
    }

    public String getFolioNC(){
        return documento.getFolio();
    }

    public String getFchEmisNC(){
        return documento.getFchEmis();
    }

    public String getIndServicioNC(){
        return documento.getIndServicio();
    }

    public String getFchVencNC(){
        return documento.getFchVenc();
    }

    public Long getIdConNC(){
        return documento.getIdCon();
    }








}
