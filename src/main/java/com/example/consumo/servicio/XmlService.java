package com.example.consumo.servicio;

import com.example.consumo.domain.*;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.EmisorService;
import com.example.consumo.service.ReceptorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class XmlService {
    @Autowired
    private ConsumoSer consumoService;
    @Autowired
    private EmisorService emisorService;
    @Autowired
    private ReceptorService receptorImpl;
    @Autowired
    private NotaCreditoRepository notaCreditoRepository;


    public String generateXmlById(Long id, Long idEmis, Long idRecep) {
        Consumo consumo = consumoService.getConsumoById(id);
        Receptor receptor = receptorImpl.getReceptorByIdRecep(idRecep);
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);


        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<Documento ID= >\n");
        xmlBuilder.append("<Encabezado>\n");

        if (consumo != null) {
            xmlBuilder.append("\t<IdDoc>\n");
            xmlBuilder.append("\t\t<tipoDte>").append(consumo.getTipoDte()).append("</tipoDte>\n");
            xmlBuilder.append("\t\t<folio>").append(consumo.getFolio()).append("</folio>\n");
            xmlBuilder.append("\t\t<fchEmis>").append(consumo.getFchEmis()).append("</fchEmis>\n");
            xmlBuilder.append("\t\t<indServicio>").append(emisor.getIndServicio()).append("</indServicio>\n");
            xmlBuilder.append("\t\t<fchVenc>").append(consumo.getFchVenc()).append("</fchVenc>\n");
            xmlBuilder.append("\t</IdDoc>\n");
        }


        if (emisor != null) {
            xmlBuilder.append("\t<Emisor>\n");
            xmlBuilder.append("\t\t<rutEmisor>").append(emisor.getRutEmisor()).append("</rutEmisor>\n");
            xmlBuilder.append("\t\t<rzonSociemisor>").append(emisor.getRzonSociemisor()).append("</rzonSociemisor>\n");
            xmlBuilder.append("\t\t<giroEmisor>").append(emisor.getGiroEmisor()).append("</giroEmisor>\n");
            xmlBuilder.append("\t\t<dirOrigen>").append(emisor.getDirOrigen()).append("</dirOrigen>\n");
            xmlBuilder.append("\t\t<cmnaEmisor>").append(emisor.getCmnaEmisor()).append("</cmnaEmisor>\n");
            xmlBuilder.append("\t\t<ciudadEmisor>").append(emisor.getCiudadEmisor()).append("</ciudadEmisor>\n");
            xmlBuilder.append("\t</Emisor>\n");
        }

        if (receptor != null) {
            xmlBuilder.append("\t<Receptor>\n");
            xmlBuilder.append("\t\t<rutReceptor>").append(receptor.getRutReceptor()).append("</rutReceptor>\n");
            xmlBuilder.append("\t\t<rznSocrecep>").append(receptor.getRznSocrecep()).append("</rznSocrecep>\n");
            xmlBuilder.append("\t\t<dirRecep>").append(receptor.getDirRecep()).append("</dirRecep>\n");
            xmlBuilder.append("\t\t<cmnaRecep>").append(receptor.getCmnaRecep()).append("</cmnaRecep>\n");
            xmlBuilder.append("\t\t<ciudadRecep>").append(receptor.getCiudadRecep()).append("</ciudadRecep>\n");
            xmlBuilder.append("\t</Receptor>\n");
        }

        if (consumo != null) {

            xmlBuilder.append("\t<Totales>\n");
            xmlBuilder.append("\t\t<mntNeto>").append(consumo.getTotal()).append("</mntNeto>\n");
            xmlBuilder.append("\t\t<mntExe>").append(consumo.getMntoEx()).append("</mntExe>\n");
            xmlBuilder.append("\t\t<IVA>").append(consumo.getIva()).append("</IVA>\n");
            xmlBuilder.append("\t\t<mntoTotal>").append(consumo.getTotal()).append("</mntoTotal>\n");
            xmlBuilder.append("\t\t<saldoAnterior>").append(consumo.getOtrosCargos()).append("</saldoAnterior>\n");
            xmlBuilder.append("\t\t<vlrPagar>").append(consumo.getTotal()).append("</vlrPagar>\n");
            xmlBuilder.append("\t</Totales>\n");
            xmlBuilder.append("\t</Encabezado>\n");
        }


        if (consumo != null) {
            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("1").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getConsumoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append("0").append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("2").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getAdminServicioNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(consumo.getUnmdItem1()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getAdminServicio()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("3").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getAdminServicioNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(consumo.getUnmdItem1()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getConsumoTotal()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("4").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<IndExe>").append("1").append("</IndExe>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(consumo.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(consumo.getTransElect()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getTransElect()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("5").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getElecConsumoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(consumo.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(consumo.getConsumoTotal()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getConsumoTotal()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("6").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getArriendoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getArriendoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(consumo.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(consumo.getArriendo()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getArriendo()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("7").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<NmbItem>").append("Otros Cargos").append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append("0").append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("8").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAjusteCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<IndExe>").append("2").append("</IndExe>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getAjusteAnt()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(consumo.getOtrosCargos()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getOtrosCargos()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("9").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAjusteCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<IndExe>").append("2").append("</IndExe>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(consumo.getAjusteAct()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(consumo.getOtrosCargos()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(consumo.getOtrosCargos()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");
        }

        xmlBuilder.append("</datos>");

        return xmlBuilder.toString();
    }

    public String generateXmlByIdNC(Long id, Long idEmis, Long idRecep) {
        Consumo consumo = consumoService.getConsumoById(id);
        Receptor receptor = receptorImpl.getReceptorByIdRecep(idRecep);
        NotaCredito notaCredito =  notaCreditoRepository.findById(id).orElse(null);
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);


        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<Documento ID= >\n");
        xmlBuilder.append("<Encabezado>\n");

        if (consumo != null) {
            xmlBuilder.append("\t<IdDoc>\n");
            xmlBuilder.append("\t\t<tipoDte>").append(consumo.getTipoDte()).append("</tipoDte>\n");
            xmlBuilder.append("\t\t<folio>").append(consumo.getFolio()).append("</folio>\n");
            xmlBuilder.append("\t\t<fchEmis>").append(consumo.getFchEmis()).append("</fchEmis>\n");
           // xmlBuilder.append("\t\t<indServicio>").append(consumo.getIndServicio()).append("</indServicio>\n");
            xmlBuilder.append("\t\t<fchVenc>").append(consumo.getFchVenc()).append("</fchVenc>\n");
            xmlBuilder.append("\t</IdDoc>\n");
        }


        if (emisor != null) {
            xmlBuilder.append("\t<Emisor>\n");
            xmlBuilder.append("\t\t<rutEmisor>").append(emisor.getRutEmisor()).append("</rutEmisor>\n");
            xmlBuilder.append("\t\t<rzonSociemisor>").append(emisor.getRzonSociemisor()).append("</rzonSociemisor>\n");
            xmlBuilder.append("\t\t<giroEmisor>").append(emisor.getGiroEmisor()).append("</giroEmisor>\n");
            xmlBuilder.append("\t\t<dirOrigen>").append(emisor.getDirOrigen()).append("</dirOrigen>\n");
            xmlBuilder.append("\t\t<cmnaEmisor>").append(emisor.getCmnaEmisor()).append("</cmnaEmisor>\n");
            xmlBuilder.append("\t\t<ciudadEmisor>").append(emisor.getCiudadEmisor()).append("</ciudadEmisor>\n");
            xmlBuilder.append("\t</Emisor>\n");
        }

        if (receptor != null) {
            xmlBuilder.append("\t<Receptor>\n");
            xmlBuilder.append("\t\t<rutReceptor>").append(receptor.getRutReceptor()).append("</rutReceptor>\n");
            xmlBuilder.append("\t\t<rznSocrecep>").append(receptor.getRznSocrecep()).append("</rznSocrecep>\n");
            xmlBuilder.append("\t\t<dirRecep>").append(receptor.getDirRecep()).append("</dirRecep>\n");
            xmlBuilder.append("\t\t<cmnaRecep>").append(receptor.getCmnaRecep()).append("</cmnaRecep>\n");
            xmlBuilder.append("\t\t<ciudadRecep>").append(receptor.getCiudadRecep()).append("</ciudadRecep>\n");
            xmlBuilder.append("\t</Receptor>\n");
        }

        if (consumo != null) {




            //MODIFICAR


            xmlBuilder.append("\t<Totales>\n");
            xmlBuilder.append("\t\t<mntNeto>").append(consumo.getTotal()).append("</mntNeto>\n");
            //xmlBuilder.append("\t\t<mntExe>").append(consumo.getTotalTpoBase()).append("</mntExe>\n");
            //xmlBuilder.append("\t\t<IVA>").append(consumo.getImpuesto()).append("</IVA>\n");
            xmlBuilder.append("\t\t<mntoTotal>").append(consumo.getTotal()).append("</mntoTotal>\n");
            xmlBuilder.append("\t\t<saldoAnterior>").append(consumo.getOtrosCargos()).append("</saldoAnterior>\n");
            //xmlBuilder.append("\t\t<vlrPagar>").append(consumo.getTotalconImpuesto()).append("</vlrPagar>\n");
            xmlBuilder.append("\t</Totales>\n");
            xmlBuilder.append("\t</Encabezado>\n");
        }


        if (notaCredito != null) {
            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("1").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<NmbItem>").append("Modifica Monto Afecto").append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(notaCredito.getMntoAfecto()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(notaCredito.getMntoAfecto()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("2").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(consumo.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(consumo.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append("Modifica Monto No Afecto").append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(consumo.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(notaCredito.getMntoNoafecto()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(notaCredito.getMntoNoafecto()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            if (consumo != null) {
                xmlBuilder.append("\t<IdDoc>\n");
                xmlBuilder.append("\t\t<tipoDte>").append(consumo.getTipoDte()).append("</tipoDte>\n");
                xmlBuilder.append("\t\t<folioRef>").append(consumo.getFolio()).append("</folioRef>\n");
                xmlBuilder.append("\t\t<fchRef>").append(consumo.getFchEmis()).append("</fchRef>\n");
                xmlBuilder.append("\t\t<codRef>").append("3").append("</codRef>\n");
                xmlBuilder.append("\t</IdDoc>\n");
            }

        }

        xmlBuilder.append("</datos>");

        return xmlBuilder.toString();
    }







}