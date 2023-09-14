package com.example.consumo.web;

import com.example.consumo.dao.NotaCreditoService;
import com.example.consumo.dao.ValorImpl;
import com.example.consumo.domain.*;
import com.example.consumo.service.ConsumoSer;
import com.example.consumo.service.EmisorService;
import com.example.consumo.service.ReceptorService;
import com.example.consumo.servicio.*;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Controller
public class FacturaController {

    @Autowired
    private final ConsumoSer consumoServices;
    @Autowired
    private final EmisorService emisorService;
    @Autowired
    private final XmlService xmlService;
    @Autowired
    private final ReceptorService receptorService;
    @Autowired
    private final PdfService pdfService;
    @Autowired
    private final PdfNC pdfNC;
    @Autowired
    private final NotaCreditoRepository notaCreditoService;
    @Autowired
    private final ValorImpl valorRepository;


    public FacturaController(ValorImpl valorRepository,NotaCreditoRepository notaCreditoService, PdfNC pdfNC, XmlService xmlService, EmisorService emisorService, ReceptorService receptorService, ConsumoSer consumoServices, PdfService pdfService) {

        this.xmlService = xmlService;
        this.consumoServices = consumoServices;
        this.emisorService = emisorService;
        this.receptorService = receptorService;
        this.pdfService = pdfService;
        this.pdfNC = pdfNC;
        this.notaCreditoService = notaCreditoService;
        this.valorRepository = valorRepository;
    }


    @GetMapping("/")
    public String listar(Model model) {
        model.addAttribute("emisors", emisorService.getAllEmisors());
        model.addAttribute("repectors", receptorService.getAll());
        model.addAttribute("consumos", consumoServices.getAllConsumos());

        return "index";
    }

    @GetMapping("/emisor")
    public String listarEmisor(Model model) {
        model.addAttribute("emisors", emisorService.getAllEmisors());
        return "emisor";
    }

    @GetMapping("/receptor")
    public String listarReceptor(Model model) {
        model.addAttribute("receptors", receptorService.getAll());

        return "receptor";
    }

    @GetMapping("/consumo")
    public String listarConsumo(Model model) {
        model.addAttribute("consumos", consumoServices.getAllConsumos());
        model.addAttribute("repectors", receptorService.getAll());
        return "consumo";
    }

    @GetMapping("/emisor/{id}")
    public String listarEmisores(@PathVariable Long id, Model model) {
        model.addAttribute("emisors", emisorService.getEmisorByIdEmis(id));
        return "emisor";
    }


    @GetMapping("/emisor/{id}/receptor")
    public String listarReceptor(@PathVariable Long id, Model model) {
        List<Receptor> receptors = receptorService.findAllByIdEmis(id);
        model.addAttribute("receptors", receptors);
        return "receptor";
    }

    @GetMapping("/{idEmis}/agregar-receptor")
    public String agregarReceptor(@PathVariable Long idEmis, Model model) {
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);
        model.addAttribute("emisor", emisor);
        model.addAttribute("receptor", new Receptor());
        return "agregar_receptor";
    }

    @PostMapping("/{idEmis}/agregar-receptor")
    public String guardarReceptor(@PathVariable Long idEmis, @ModelAttribute Receptor receptor) {
        receptorService.saveReceptor(idEmis, receptor);
        return "receptor";
    }

    @GetMapping("/receptor/{id}/consumo")
    public String listarConsumos(@PathVariable Long id, Model model) {
        List<Consumo> consumo = consumoServices.getConsumosByIdRecep(id);
        model.addAttribute("consumos", consumo);

        return "consumo";
    }


    @GetMapping("/agregar-emisor")
    public String agregarEmisor(Model model) {
        model.addAttribute("emisor", new Emisor());
        return "agregar_emisor";
    }

    @GetMapping("/{idEmis}/agregar-valor")
    public String agregarValor(@PathVariable Long idEmis, Model model) {
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);
        Valor valor = new Valor();

        model.addAttribute("valor", valor);
        model.addAttribute("emisor", emisor);
        return "agregar_valor";
    }
    @PostMapping("/salvar-valor/{idEmis}")
    public String guardarValor(@PathVariable Long idEmis, @ModelAttribute Valor valor) {
    valorRepository.saveValor(idEmis, valor);


        return "redirect:/receptor";
    }







    @GetMapping("/agregar-consumo/{idRecep}/{idEmis}")
    public String agregarConsumo(@PathVariable Long idRecep, @PathVariable Long idEmis, Model model) {
        Receptor receptores = receptorService.getReceptorByIdRecep(idRecep);
        Consumo ultimoConsumo = consumoServices.getUltimoConsumoByReceptorId(idRecep);
        // Obtener el último consumo
        Consumo nuevoConsumo = new Consumo();
        if (ultimoConsumo != null) {
            nuevoConsumo.setLecturaAnt1(ultimoConsumo.getLecturaAct2()); // Setear lecturaAnt1 del último consumo
        }
        model.addAttribute("nuevoConsumo", nuevoConsumo);
        model.addAttribute("receptores", receptores );
        return "agregar_consumo";
    }





    @PostMapping("/salvar-consumo/{idRecep}/{idEmis}")
    public String guardarConsumo(@ModelAttribute Consumo consumo, Valor valor,@PathVariable Long idRecep, @PathVariable Long idEmis) {
    consumo.calcularMontos();
    consumoServices.calcularValores(idEmis, consumo);
    consumoServices.guardarConsumo(idRecep, idEmis, consumo);



        return "redirect:/receptor";
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



    @GetMapping("/receptor/{id}/eliminar")
    public String eliminarReceptor(@PathVariable("id") Long id) {
        receptorService.deleteReceptorByIdRecep(id);
        return "redirect:/emisor/{id}/receptor";
    }


    @GetMapping("/emisor/{id}/eliminar")
    public String eliminarEmisor(@PathVariable("id") Long id) {
        emisorService.eliminaEmisor(id);
        return "redirect:/emisor";
    }

    @GetMapping("/consumo/{id}/eliminar")
    public String eliminarConsumo(@PathVariable("id") Long id) {
        consumoServices.eliminarConsumo(id);
        return "redirect:/consumo";
    }

    @PostMapping("/guardar3")
    public String guardarEmisor(@ModelAttribute Emisor emisor) {
        emisorService.guardarEmisor(emisor);
        return "redirect:/emisor";
    }
    @PostMapping("/{idEmis}/guardar4")
    public String guardarReceptor1(@PathVariable Long idEmis,@ModelAttribute Receptor receptor) {
        Receptor receptors = receptorService.getReceptorByIdEmis(idEmis);
        receptorService.saveReceptor( idEmis,receptor);
        return "redirect:/emisor";
    }



    @GetMapping("/receptor/{idEmis}/editar")
    public String mostrarFormularioEditarReceptor(@PathVariable Long idEmis, Model model) {
        Receptor receptor = receptorService.getReceptorByIdRecep(idEmis);
        model.addAttribute("receptor", receptor);
        return "editar_receptor";
    }


   /* @GetMapping("/consumo/{id}/editar")
    public String mostrarFormularioEditarConsumo(@PathVariable Long id, Model model) {
        Consumo consumo = consumoServices.getConsumoById(id);
        model.addAttribute("consumo", consumo );

        return "editar_consumo";s
    }

    */



    @GetMapping("/consumo-editar/{id}/{idRecep}")
    public String editarNotaDeCredito(@PathVariable Long id,@PathVariable Long idRecep, Model model) {
        // Obtener el consumo por su folio
        Consumo consumo = consumoServices.getConsumoById(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(idRecep);

        // Pasar los valores double del consumo a la página
        model.addAttribute("consumo", consumo );
        model.addAttribute("consumoKwh", consumo.getConsumoKwh());
        model.addAttribute("adminServicio", consumo.getAdminServicio());
        model.addAttribute("transElect", consumo.getTransElect());
        model.addAttribute("arriendo", consumo.getArriendo());
        model.addAttribute("consumoTotal", consumo.getConsumoTotal());

        return "editar_consumo"; // Página para editar la nota de crédito
    }

    @PostMapping("/editar-consumo/{id}")
    public NotaCredito generarNotaCredito(@PathVariable Long id, Consumo consumo) {
        Consumo consumoActual = consumoServices.getConsumoById(id);
        Double consumoAnterior = consumoActual.getConsumoTotal();
        //Double diferenciaConsumo = nuevoConsumo - consumoAnterior;

        // Calcular monto no afecto (prcCargo)
        //Double montoNoAfecto =  consumoActual.getPrcCargo();

        // Calcular monto afecto (suma de adminServicio, transElect, consumoTotal y arriendo)
        Double montoAfecto = consumoActual.getAdminServicio() + consumoActual.getTransElect()
                + consumoActual.getConsumoTotal() + consumoActual.getArriendo();

        // Actualizar el consumo
        //consumoActual.setConsumoTotal(nuevoConsumo);
        //consumoServices.guardarConsumo(consumoActual);

        // Crear una nueva nota de crédito
        NotaCredito notaCredito = new NotaCredito();
        notaCredito.setMntoAfecto(montoAfecto);
        //notaCredito.setMntoNoafecto(montoNoAfecto);
        notaCredito.setIva(montoAfecto * 0.19); // Suponiendo un 19% de IVA
        //notaCredito.setTotal(montoAfecto + montoNoAfecto + notaCredito.getIva());

        // Otras asignaciones de atributos según tus necesidades

        notaCreditoService.save(notaCredito);

        return notaCredito;
    }


    /*@PostMapping("/editar-consumo/{idRecep}/{idEmis}")
    public String editarConsumo( @ModelAttribute Consumo consumo,@PathVariable Long idRecep,@PathVariable Long idEmis, @RequestParam Double adminServicio,
                                 @RequestParam Double transElect,
                                 @RequestParam Double consumoTotal,
                                 @RequestParam Double arriendo) {
        Receptor receptor = receptorService.getReceptorByIdRecep(idRecep);
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);
        consumo.calcularMontos();
        consumoServices.calcularValores(idRecep, consumo);
        consumoServices.guardarConsumo(idRecep, idEmis, consumo);

        // Crear una nueva nota de crédito y guardarla en la base de datos
        notaCreditoService.crearNotaDeCredito(consumo, adminServicio,
                transElect, consumoTotal, arriendo, emisor.getPrcTpobase());
        return "redirect:/receptor";
    }

     */



    @GetMapping("/emisor/{id}/editar")
    public String mostrarFormularioEditarEmisor(@PathVariable Long id, Model model) {
        Emisor emisor = emisorService.getEmisorByIdEmis(id);
        model.addAttribute("emisor", emisor);
        return "editar_emisor";
    }



    @GetMapping("/receptor/{idRecep}/generar-pdf/consumo")
    public ResponseEntity<byte[]> generarBoletaPDF(@PathVariable Long idRecep, Model model) throws JRException, FileNotFoundException {
        byte[] pdfBytes = pdfService.generarBoletaPDF(idRecep);
        //Consumo consumo = consumoServices.getConsumoById(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(idRecep);
        //model.addAttribute("consumo", consumo);
        model.addAttribute("receptor", receptor);

        if (pdfBytes == null) {
            // Manejar caso si el PDF no se pudo generar
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "boleta.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }

    @GetMapping("/receptor/{idRecep}/generar-pdfNC/consumo")
    public ResponseEntity<byte[]> generarBoletaPDF2(@PathVariable Long idRecep, Model model) throws JRException, FileNotFoundException {
        byte[] pdfBytes = pdfService.generarBoletaPDF2(idRecep);
        //Consumo consumo = consumoServices.getConsumoById(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(idRecep);
       // model.addAttribute("consumo", consumo);
        model.addAttribute("receptor", receptor);

        if (pdfBytes == null) {
            // Manejar caso si el PDF no se pudo generar
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("inline", "boleta.pdf");

        return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    }







    @GetMapping("/{idEmis}/{idRecep}/consumo/{id}/imprimir-xml")
    public String imprimirXml(@PathVariable Long id, @PathVariable Long idEmis,@PathVariable Long idRecep, Model model) {
        String xmlData = xmlService.generateXmlById(id, idEmis, idRecep);
        Consumo consumo = consumoServices.getConsumoById(id);
        Receptor receptor = receptorService.getReceptorByIdEmis(idRecep);
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);
        model.addAttribute("consumo", consumo);
        model.addAttribute("receptor", receptor);
        model.addAttribute("emisor", emisor);
        model.addAttribute("xmlData", xmlData);


        return "xml-view";

    }

    @GetMapping("/agregar-nc/{id}")
    public String agregarNota(Model model,@PathVariable Long id) {
        Consumo consumo = consumoServices.getConsumoById(id);
        model.addAttribute("notaCredito", new NotaCredito());
        model.addAttribute("consumo", consumo);
        return "nota_credito";
    }




    @PostMapping("/salvar-credito")
    public String calcularMontos(Consumo consumo, NotaCredito notaCredito) {

        // Actualizar los valores en la entidad Consumo y guardarlos en la base de datos
       // notaCreditoService.save(notaCredito);

         ResponseEntity.ok("Cálculos realizados y valores actualizados en la base de datos");
         return "redirect:/consumo";
    }












    @GetMapping("/{idEmis}/{idRecep}/consumo/{id}/imprimir-xmlNC")
    public String imprimirXmlNC(@PathVariable Long id, @PathVariable Long idRecep,@PathVariable Long idEmis, Model model) {
        String xmlData = xmlService.generateXmlByIdNC(id, idEmis, idRecep);
        Consumo consumo = consumoServices.getConsumoById(id);
        Receptor receptor = receptorService.getReceptorByIdRecep(idRecep);
       // NotaCredito notaCredito = notaCreditoService.findById(id).orElse(null);
        Emisor emisor = emisorService.getEmisorByIdEmis(idEmis);
        model.addAttribute("consumo", new Consumo());
        model.addAttribute("receptor", receptor);
        model.addAttribute("emisor", emisor);
        //model.addAttribute("notaCredito", notaCredito);
        model.addAttribute("xmlData", xmlData);


        return "xml-view";

    }












    @GetMapping("/{idEmis}/{idRecep}/consumo/{id}/download-xml")
    public ResponseEntity<Resource> downloadXmlById(@PathVariable Long id, @PathVariable Long idEmis,@PathVariable Long idRecep, Model model) {

        // Generar el XML
        String xmlData = xmlService.generateXmlById(id, idEmis, idRecep);
        model.addAttribute("receptor", receptorService.getReceptorByIdRecep(idRecep));
        model.addAttribute("consumo", consumoServices.getConsumoById(id));
        model.addAttribute("emisor", emisorService.getEmisorByIdEmis(idEmis));

        // Crear un recurso de tipo ByteArrayResource para el contenido del XML
        ByteArrayResource resource = new ByteArrayResource(xmlData.getBytes());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=data.xml")
                .contentType(MediaType.APPLICATION_XML)
                .contentLength(xmlData.getBytes().length)
                .body(resource);
    }


    @GetMapping("/documento/{id}/info_documento")
    public String infoDocumento(@PathVariable Long id, Model model) {
        List<Receptor> receptor = receptorService.getAll();
        Consumo consumos = consumoServices.getConsumoById(id);


        model.addAttribute("receptor", receptor);
        model.addAttribute("consumos", consumos);


        return "info_documento";
    }





}

