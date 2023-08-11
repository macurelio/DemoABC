package com.example.consumo.web;

import com.example.consumo.domain.*;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.DocumentoService;
import com.example.consumo.service.ReceptorService;
import com.example.consumo.servicio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;


@Controller
public class FacturaController {

    @Autowired
    private final ConsumoSer consumoServices;
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final EmisorRepository emisorRepository;
    @Autowired
    private final XmlService xmlService;
    @Autowired
    private final ReceptorService receptorService;
    @Autowired
    private final DocumentoService documentoService;
    @Autowired
    private final DetalleRepository detalleRepository;
    @Autowired
    private final TotalesRepository totalesRepository;
    @Autowired
    private final PdfService pdfService;
    @Autowired
    private final PdfNC pdfNC;


    public FacturaController(PdfNC  pdfNC,XmlService xmlService, EmisorRepository emisorRepository, ReceptorService receptorService, DocumentoService documentoService, DetalleRepository detalleRepository, TotalesRepository totalesRepository, ConsumoSer consumoServices, ItemRepository itemRepository, PdfService pdfService) {

        this.xmlService = xmlService;
        this.consumoServices = consumoServices;
        this.emisorRepository = emisorRepository;
        this.receptorService = receptorService;
        this.documentoService = documentoService;
        this.detalleRepository = detalleRepository;
        this.totalesRepository = totalesRepository;
        this.itemRepository = itemRepository;
        this.pdfService = pdfService;
        this.pdfNC = pdfNC;
    }


    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("emisors", emisorRepository.findAll());
        model.addAttribute("repectors", receptorService.getAll());
        model.addAttribute("consumos", consumoServices.getAll());
        model.addAttribute("documentos", documentoService.getAll());

        return "index";
    }

    @GetMapping("/emisor")
    public String listarEmisor(Model model) {
        model.addAttribute("emisors", emisorRepository.findAll());
        return "emisor";
    }

    @GetMapping("/receptor")
    public String listarReceptor(Model model) {
        model.addAttribute("receptors", receptorService.getAll());
        return "receptor";
    }

    @GetMapping("/documento")
    public String listarDocumentos(Model model) {
        model.addAttribute("documentos", documentoService.getAll());
        return "documento";
    }

    @GetMapping("/consumo")
    public String listarConsumo(Model model) {
        model.addAttribute("consumos", consumoServices.getAll());
        model.addAttribute("repectors", receptorService.getAll());
        return "consumo";
    }

    @GetMapping("/emisor/{id}")
    public String listarEmisores(@PathVariable Long id, Model model) {
        model.addAttribute("emisors", emisorRepository.findByIdEmis(id));
        return "emisor";
    }


    @GetMapping("/emisor/{id}/receptor")
    public String listarReceptor(@PathVariable Long id, Model model) {
        model.addAttribute("receptors", receptorService.getAll());
        return "receptor";
    }


    @GetMapping("/receptor/{id}/consumo")
    public String listarConsumos(@PathVariable Long id, Model model) {
        model.addAttribute("receptor", receptorService.getReceptorByIdRecep(id));
        model.addAttribute("consumos", consumoServices.findAllByIdRecep(id));

        return "consumo";
    }


    @GetMapping("/consumo/{id}/documento")
    public String listarDocumento(@PathVariable Long id, Model model) {
        model.addAttribute("consumos", consumoServices.getConsumoByIdRecep(id));
        model.addAttribute("documentos", documentoService.findByIdDoc(id));
        return "documento";
    }


    @GetMapping("/agregar-emisor")
    public String agregarEmisor(Model model) {
        model.addAttribute("emisor", new Emisor());
        return "agregar_emisor";
    }


    @GetMapping("/receptor/{id}/generar-consumo")
    public String agregarConsumo(@PathVariable Long id, Model model) {
        Consumo consumo = consumoServices.getConsumoByIdRecep(id);
        model.addAttribute("consumo", consumo);
        model.addAttribute("receptor", new Consumo());
        return "agregar_consumo";
    }

    @PostMapping("/salvar-consumo")
    public String guardarConsumo(Consumo consumo) {

        consumoServices.saveReceptorByIdRecep(consumo);
        return "redirect:/receptor";
    }


    @GetMapping("/agregar-documento")
    public String agregarDocumento( Model model) {
        model.addAttribute("documento", new Documento());
        return "agregar_documento";
    }

    @GetMapping("/agregar-receptor")
    public String agregarReceptor(Model model) {
        model.addAttribute("receptor", new Receptor());
        return "agregar_receptor";
    }


    @GetMapping("/regresar")
    public String regresar() {
        return "redirect:/";
    }

    @GetMapping("/regresarConsumo")
    public String regresarConsumo() {
        return "redirect:/consumo";
    }

    @GetMapping("/regresarReceptor")
    public String regresarReceptor() {
        return "redirect:/receptor";
    }

    @GetMapping("/regresarEmisor")
    public String regresarEmisor() {
        return "redirect:/emisor";
    }

    @GetMapping("/regresarDocumento")
    public String regresarDocumento() {
        return "redirect:/documento";
    }


    @GetMapping("/receptor/{id}/eliminar")
    public String eliminarReceptor(@PathVariable("id") Long id) {
        receptorService.deleteReceptorByIdRecep(id);
        return "redirect:/emisor/{id}/receptor";
    }

    @GetMapping("/documento/{id}/eliminar")
    public String eliminarDocumento(@PathVariable("id") Long id) {
        documentoService.deleteReceptorByIdCon(id);
        return "redirect:/consumo/{id}/documento";
    }

    @GetMapping("/emisor/{id}/eliminar")
    public String eliminarEmisor(@PathVariable("id") Long id) {
        emisorRepository.deleteById(id);
        return "redirect:/emisor";
    }

    @GetMapping("/consumo/{id}/eliminar")
    public String eliminarConsumo(@PathVariable("id") Long id) {
        consumoServices.deleteReceptorByIdCon(id);
        return "redirect:/consumo";
    }


    @PostMapping("/guardar4")
    public String guardarReceptor(@ModelAttribute Receptor receptor) {
        receptorService.saveReceptor(receptor);
        return "redirect:/receptor";
    }



    @PostMapping("/guardar2")
    public String guardarDocumento(@ModelAttribute Documento documento) {
        documentoService.saveReceptorByIdDoc(documento);
        return "redirect:/documento";
    }

    @PostMapping("/guardar3")
    public String guardarEmisor(@ModelAttribute Emisor emisor) {
        emisorRepository.save(emisor);
        return "redirect:/emisor";
    }


    @GetMapping("/documento/{id}/editar")
    public String mostrarFormularioEditarDocumento(@PathVariable Long id, Model model) {
        Documento documento = documentoService.getConsumoByIdCon(id);
        model.addAttribute("documento", documento);
        return "editar_documento";
    }


    @GetMapping("/receptor/{id}/editar")
    public String mostrarFormularioEditarReceptor(@PathVariable Long id, Model model) {
        Receptor receptor = receptorService.getReceptorByIdRecep(id);
        model.addAttribute("receptor", receptor);
        return "editar_receptor";
    }


    @GetMapping("/consumo/{id}/editar")
    public String mostrarFormularioEditarConsumo(@PathVariable Long id, Model model) {
        Consumo consumo = consumoServices.findByIdCon(id);
        model.addAttribute("consumo", consumo);
        return "editar_consumo";
    }

    @GetMapping("/emisor/{id}/editar")
    public String mostrarFormularioEditarEmisor(@PathVariable Long id, Model model) {
        Optional<Emisor> emisor = emisorRepository.findById(id);
        model.addAttribute("emisor", emisor);
        return "editar_emisor";
    }


    @GetMapping("/{id}/generar-informe")
    public ResponseEntity generarInforme(@PathVariable Long id) {
        try {
            byte[] reportBytes = pdfService.generarInformePorIdCliente(id);

            // Configurar la cabecera de la respuesta HTTP
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=informe.pdf");

            // Crear el recurso del archivo PDF para la descarga
            ByteArrayResource resource = new ByteArrayResource(reportBytes);

            // Crear la respuesta con el archivo adjunto
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/consumo/{id}/imprimir-xml/receptor/{id1}/consumo")
    public String imprimirXml(@PathVariable Long id, @PathVariable Long id1, Model model) {
        String xmlData = xmlService.generateXmlById(id, id1);
        Consumo consumo = consumoServices.findByIdCon(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(id1);
        Documento documento = documentoService.getConsumoByIdCon(id);
        model.addAttribute("consumo", consumo);
        model.addAttribute("receptor", receptor);
        model.addAttribute("documento", documento);
        model.addAttribute("xmlData", xmlData);


        return "xml-view";

    }


    @GetMapping("/consumo/{id}/imprimir-xmlNC/receptor/{id1}/consumo")
    public String imprimirXmlNC(@PathVariable Long id, @PathVariable Long id1, Model model) {
        String xmlData = xmlService.generateXmlByIdNC(id, id1);
        Consumo consumo = consumoServices.findByIdCon(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(id1);
        Documento documento = documentoService.getConsumoByIdCon(id);
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("receptor", receptor);
        model.addAttribute("documento", documento);
        model.addAttribute("xmlData", xmlData);


        return "xml-view";

    }












    @GetMapping("/consumo/{id}/download-xml/receptor/{id1}/consumo")
    public ResponseEntity<Resource> downloadXmlById(@PathVariable Long id, @PathVariable Long id1, Model model) {

        // Generar el XML
        String xmlData = xmlService.generateXmlById(id, id1);
        model.addAttribute("receptor", receptorService.getReceptorByIdRecep(id1));
        model.addAttribute("consumo", consumoServices.findAllByIdRecep(id));
        model.addAttribute("documento", documentoService.getConsumoByIdCon(id));

        // Crear un recurso de tipo ByteArrayResource para el contenido del XML
        ByteArrayResource resource = new ByteArrayResource(xmlData.getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=data.xml")
                .contentType(MediaType.APPLICATION_XML)
                .contentLength(xmlData.getBytes().length)
                .body(resource);
    }


    @GetMapping("documento/{id}/info_documento")
    public String infoDocumento(@PathVariable Long id, Model model) {
        List<Receptor> receptor = receptorService.getAll();
        Documento documentos = documentoService.findByIdDoc(id);
        Consumo consumos = consumoServices.findByIdCon(id);


        model.addAttribute("receptor", receptor);
        model.addAttribute("documentos", documentos);
        model.addAttribute("consumos", consumos);


        return "info_documento";
    }




}

