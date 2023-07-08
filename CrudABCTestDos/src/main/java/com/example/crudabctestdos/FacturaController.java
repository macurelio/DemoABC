package com.example.crudabctestdos;

import com.example.crudabctestdos.model.*;
import com.example.crudabctestdos.repository.*;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@Controller
public class FacturaController {
    private final ItemRepository itemRepository;
    private final EmisorRepository emisorRepository;
    private final ReceptorRepository receptorRepository;
    private final DocumentoRepository documentoRepository;
    private final DetalleRepository detalleRepository;

    public FacturaController(ItemRepository itemRepository,
                             EmisorRepository emisorRepository,
                             ReceptorRepository receptorRepository,
                             DocumentoRepository documentoRepository,
                             DetalleRepository detalleRepository) {

        this.itemRepository = itemRepository;
        this.emisorRepository = emisorRepository;
        this.receptorRepository = receptorRepository;
        this.documentoRepository = documentoRepository;
        this.detalleRepository = detalleRepository;

    }




    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        model.addAttribute("documentos", documentoRepository.findAll());
        model.addAttribute("emisors", emisorRepository.findAll());
        model.addAttribute("receptors", receptorRepository.findAll());
        model.addAttribute("detalles", receptorRepository.findAll());
        return "index";
    }

        @GetMapping("/agregar")
        public String agregarCliente(Model model) {
            model.addAttribute("item", new Item());
            model.addAttribute("documento", new Documento());
            model.addAttribute("emisor", new Emisor());
            model.addAttribute("receptor", new Receptor());
            model.addAttribute("detalle", new Detalle());
            return "agregar_clientesdat";
        }
    @GetMapping("/siguiente")
    public String agregarFactura(Model model) {
        model.addAttribute("documento", new Documento());
        return "agregar_factura";
    }
    @GetMapping("/regresar")
    public String regresar() {
        return "redirect:/";
    }
    @GetMapping("/item/{id}/eliminar")
    public String eliminarCliente(@PathVariable("id") Long id) {
        itemRepository.deleteById(id);
        return "redirect:/";
    }
    @GetMapping("/listarCtaCorriente")
    public String listarCuentaCorriente( Model model) {
        model.addAttribute("emisors", emisorRepository.findAll());
        return "cuenta_corriente";
    }

        @PostMapping("/guardar")
        public String guardarFactura(Item item) {
            itemRepository.save(item);
            return "redirect:/";
        }
    @PostMapping("/guardar1")
    public String guardarFactura(Documento documento) {
        documentoRepository.save(documento);
        return "redirect:/";
    }

        @GetMapping("/item/{id}/calcular")
        public String calcular(@PathVariable("id") Long id,Model model) {
            Double suma = itemRepository.findById(id)
                    .stream()
                    .mapToDouble(Item::getTotalconImpuesto)
                    .sum();
            Double total = itemRepository.findById(id)
                    .stream()
                    .mapToDouble(Item::getTotal)
                    .sum();

            Double impuesto = itemRepository.findById(id)
                    .stream()
                    .mapToDouble(Item::getImpuesto)
                    .sum();

            Double totalConImpuesto = suma ;
            totalConImpuesto = Math.ceil(totalConImpuesto);
            totalConImpuesto = totalConImpuesto - (totalConImpuesto % 100);

model.addAttribute("total", total);
            model.addAttribute("suma", suma);
            model.addAttribute("impuesto", impuesto);
            model.addAttribute("totalConImpuesto", totalConImpuesto);

            return "calcular";
        }
    @GetMapping ("/item/{id}/imprimir-xml")
    public String imprimirXml(@PathVariable("id") Long id,Model model) {
        List<Documento> documentos = documentoRepository.findByIdDoc(id);
        List<Emisor> emisors = emisorRepository.findByIdDoc(id);
        List<Receptor> receptors = receptorRepository.findByIdDoc(id);
        List<Item> items0 = itemRepository.findByIdDoc(id);
        List<Item> items = itemRepository.findByIdDoc(id);
        List<Detalle> detalles = detalleRepository.findByIdDoc(id);



        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<Documento ID= >\n");
        xmlBuilder.append("<Encabezado>\n");
        for (Documento entity0 : documentos) {
            xmlBuilder.append("\t<IdDoc>\n");
            xmlBuilder.append("\t\t<tipoDte>").append(entity0.getTipoDte()).append("</tipoDte>\n");
            xmlBuilder.append("\t\t<folio>").append(entity0.getFolio()).append("</folio>\n");
            xmlBuilder.append("\t\t<fchEmis>").append(entity0.getFchEmis()).append("</fchEmis>\n");
            xmlBuilder.append("\t\t<indServicio>").append(entity0.getIndServicio()).append("</indServicio>\n");
            xmlBuilder.append("\t\t<fchVenc>").append(entity0.getFchVenc()).append("</fchVenc>\n");
            xmlBuilder.append("\t</IdDoc>\n");

        }
        for (Emisor entity1 : emisors) {
            xmlBuilder.append("\t<Emisor>\n");
            xmlBuilder.append("\t\t<rutEmisor>").append(entity1.getRutEmisor()).append("</rutEmisor>\n");
            xmlBuilder.append("\t\t<rzonSociemisor>").append(entity1.getRzonSociemisor()).append("</rzonSociemisor>\n");
            xmlBuilder.append("\t\t<giroEmisor>").append(entity1.getGiroEmisor()).append("</giroEmisor>\n");
            xmlBuilder.append("\t\t<dirOrigen>").append(entity1.getDirOrigen()).append("</dirOrigen>\n");
            xmlBuilder.append("\t\t<cmnaEmisor>").append(entity1.getCmnaEmisor()).append("</cmnaEmisor>\n");
            xmlBuilder.append("\t\t<ciudadEmisor>").append(entity1.getCiudadEmisor()).append("</ciudadEmisor>\n");
            xmlBuilder.append("\t</Emisor>\n");
        }
        for (Receptor entity2 : receptors) {
            xmlBuilder.append("\t<Receptor>\n");
            xmlBuilder.append("\t\t<rutReceptor>").append(entity2.getRutReceptor()).append("</rutReceptor>\n");
            xmlBuilder.append("\t\t<rznSocrecep>").append(entity2.getRznSocrecep()).append("</rznSocrecep>\n");
            xmlBuilder.append("\t\t<dirRecep>").append(entity2.getDirRecep()).append("</dirRecep>\n");
            xmlBuilder.append("\t\t<cmnaRecep>").append(entity2.getCmnaRecep()).append("</cmnaRecep>\n");
            xmlBuilder.append("\t\t<ciudadRecep>").append(entity2.getCiudadRecep()).append("</ciudadRecep>\n");
            xmlBuilder.append("\t</Receptor>\n");
        }

        xmlBuilder.append("\t<Totales>\n");


        for (Item entity4 : items) {
            xmlBuilder.append("\t\t<mntNeto>").append(entity4.getNeto()).append("</mntNeto>\n");
            xmlBuilder.append("\t\t<mntExe>").append(entity4.getExcento()).append("</mntExe>\n");
            xmlBuilder.append("\t\t<IVA>").append(entity4.getImpuesto()).append("</IVA>\n");
            xmlBuilder.append("\t\t<mntoTotal>").append(entity4.getTotal()).append("</mntoTotal>\n");
            xmlBuilder.append("\t\t<vlrPagar>").append(entity4.getTotalconImpuesto()).append("</vlrPagar>\n");

        }
        xmlBuilder.append("\t</Totales>\n");
        xmlBuilder.append("\t</Encabezado>\n");

        for (Detalle entity : detalles) {

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("1").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getConsumoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append("0").append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("2").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getAdminServicioNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem1()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getAdminServicio()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("3").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getTransElecCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<transElec>").append(entity.getTransElec()).append("</transElec>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getTransElec()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("4").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<IndExe>").append("1").append("</IndExe>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append("31").append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getTransElec()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("5").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getElecConsumoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(entity.getConsumo()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getConsumo()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("6").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getArriendoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getArriendoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(entity.getArriendo()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getArriendo()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            for (Item entity4 : items) {
                xmlBuilder.append("\t<Detalle>\n");
                xmlBuilder.append("\t\t<NroLinDet>").append("7").append("</NroLinDet>\n");
                xmlBuilder.append("\t\t<NmbItem>").append(entity.getSaldoAnteriorNombre()).append("</NmbItem>\n");
                xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
                xmlBuilder.append("\t\t<MontoItem>").append(entity4.getSaldoAnterior()).append("</MontoItem>\n");
                xmlBuilder.append("\t</Detalle>\n");
            }

            ////////

            xmlBuilder.append("\t\t<saldoAnterior>").append(entity.getSaldoAnterior()).append("</saldoAnterior>\n");
            xmlBuilder.append("\t</Totales>\n");

        }


        xmlBuilder.append("</data>");
        model.addAttribute("xmlData", xmlBuilder.toString());
        return "xml-view";
    }


    @GetMapping("/item/{id}/download")
    public void downloadXml(HttpServletResponse response, @PathVariable("id") Long id) throws IOException {
        List<Documento> documentos = documentoRepository.findByIdDoc(id);
        List<Emisor> emisors = emisorRepository.findByIdDoc(id);
        List<Receptor> receptors = receptorRepository.findByIdDoc(id);
        List<Item> items0 = itemRepository.findByIdDoc(id);
        List<Item> items = itemRepository.findByIdDoc(id);
        List<Detalle> detalles = detalleRepository.findByIdDoc(id);



        StringBuilder xmlBuilder = new StringBuilder();
        xmlBuilder.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xmlBuilder.append("<Documento ID= >\n");
        xmlBuilder.append("<Encabezado>\n");
        for (Documento entity0 : documentos) {
            xmlBuilder.append("\t<IdDoc>\n");
            xmlBuilder.append("\t\t<tipoDte>").append(entity0.getTipoDte()).append("</tipoDte>\n");
            xmlBuilder.append("\t\t<folio>").append(entity0.getFolio()).append("</folio>\n");
            xmlBuilder.append("\t\t<fchEmis>").append(entity0.getFchEmis()).append("</fchEmis>\n");
            xmlBuilder.append("\t\t<indServicio>").append(entity0.getIndServicio()).append("</indServicio>\n");
            xmlBuilder.append("\t\t<fchVenc>").append(entity0.getFchVenc()).append("</fchVenc>\n");
            xmlBuilder.append("\t</IdDoc>\n");

        }
        for (Emisor entity1 : emisors) {
            xmlBuilder.append("\t<Emisor>\n");
            xmlBuilder.append("\t\t<rutEmisor>").append(entity1.getRutEmisor()).append("</rutEmisor>\n");
            xmlBuilder.append("\t\t<rzonSociemisor>").append(entity1.getRzonSociemisor()).append("</rzonSociemisor>\n");
            xmlBuilder.append("\t\t<giroEmisor>").append(entity1.getGiroEmisor()).append("</giroEmisor>\n");
            xmlBuilder.append("\t\t<dirOrigen>").append(entity1.getDirOrigen()).append("</dirOrigen>\n");
            xmlBuilder.append("\t\t<cmnaEmisor>").append(entity1.getCmnaEmisor()).append("</cmnaEmisor>\n");
            xmlBuilder.append("\t\t<ciudadEmisor>").append(entity1.getCiudadEmisor()).append("</ciudadEmisor>\n");
            xmlBuilder.append("\t</Emisor>\n");
        }
        for (Receptor entity2 : receptors) {
            xmlBuilder.append("\t<Receptor>\n");
            xmlBuilder.append("\t\t<rutReceptor>").append(entity2.getRutReceptor()).append("</rutReceptor>\n");
            xmlBuilder.append("\t\t<rznSocrecep>").append(entity2.getRznSocrecep()).append("</rznSocrecep>\n");
            xmlBuilder.append("\t\t<dirRecep>").append(entity2.getDirRecep()).append("</dirRecep>\n");
            xmlBuilder.append("\t\t<cmnaRecep>").append(entity2.getCmnaRecep()).append("</cmnaRecep>\n");
            xmlBuilder.append("\t\t<ciudadRecep>").append(entity2.getCiudadRecep()).append("</ciudadRecep>\n");
            xmlBuilder.append("\t</Receptor>\n");
        }

        xmlBuilder.append("\t<Totales>\n");


        for (Item entity4 : items) {
            xmlBuilder.append("\t\t<mntNeto>").append(entity4.getNeto()).append("</mntNeto>\n");
            xmlBuilder.append("\t\t<mntExe>").append(entity4.getExcento()).append("</mntExe>\n");
            xmlBuilder.append("\t\t<IVA>").append(entity4.getImpuesto()).append("</IVA>\n");
            xmlBuilder.append("\t\t<mntoTotal>").append(entity4.getTotal()).append("</mntoTotal>\n");
            xmlBuilder.append("\t\t<vlrPagar>").append(entity4.getTotalconImpuesto()).append("</vlrPagar>\n");

        }
        xmlBuilder.append("\t</Totales>\n");
        xmlBuilder.append("\t</Encabezado>\n");

        for (Detalle entity : detalles) {

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("1").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getConsumoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append("0").append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("2").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getAdminServicioNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem1()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getAdminServicio()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("3").append("</NroLinDet>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getTransElecCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<transElec>").append(entity.getTransElec()).append("</transElec>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getTransElec()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("4").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getAdminServCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<IndExe>").append("1").append("</IndExe>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append("31").append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getTransElec()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");


            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("5").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getElecConsumoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.geTransElecNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(entity.getConsumo()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getConsumo()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            xmlBuilder.append("\t<Detalle>\n");
            xmlBuilder.append("\t\t<NroLinDet>").append("6").append("</NroLinDet>\n");
            xmlBuilder.append("\t<CdgItem>\n");
            xmlBuilder.append("\t\t<TpoCodigo>").append(entity.getTpoCodigo()).append("</TpoCodigo>\n");
            xmlBuilder.append("\t\t<VlrCodigo>").append(entity.getArriendoCodigo()).append("</VlrCodigo>\n");
            xmlBuilder.append("\t</CdgItem>\n");
            xmlBuilder.append("\t\t<NmbItem>").append(entity.getArriendoNombre()).append("</NmbItem>\n");
            xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
            xmlBuilder.append("\t\t<UnmdItem>").append(entity.getUnmdItem2()).append("</UnmdItem>\n");
            xmlBuilder.append("\t\t<PrcItem>").append(entity.getArriendo()).append("</PrcItem>\n");
            xmlBuilder.append("\t\t<MontoItem>").append(entity.getArriendo()).append("</MontoItem>\n");
            xmlBuilder.append("\t</Detalle>\n");

            for (Item entity4 : items) {
                xmlBuilder.append("\t<Detalle>\n");
                xmlBuilder.append("\t\t<NroLinDet>").append("7").append("</NroLinDet>\n");
                xmlBuilder.append("\t\t<NmbItem>").append(entity.getSaldoAnteriorNombre()).append("</NmbItem>\n");
                xmlBuilder.append("\t\t<QtyItem>").append(entity.getAllQtyItem()).append("</QtyItem>\n");
                xmlBuilder.append("\t\t<MontoItem>").append(entity4.getAjusteActual()).append("</MontoItem>\n");
                xmlBuilder.append("\t</Detalle>\n");
            }

            ////////

            xmlBuilder.append("\t\t<saldoAnterior>").append(entity.getSaldoAnterior()).append("</saldoAnterior>\n");
            xmlBuilder.append("\t</Totales>\n");

        }


        xmlBuilder.append("</data>");
        response.setContentType("application/xml");
        response.setHeader("Content-Disposition", "attachment; filename=data.xml");


        response.getWriter().write(xmlBuilder.toString());
    }


    @GetMapping("/listar")
    public String listarPorId(@RequestParam("id") Long id, Model model) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Totales no encontrada con ID: " + id));

        model.addAttribute("item", item);

        return "listar";
    }
    }

