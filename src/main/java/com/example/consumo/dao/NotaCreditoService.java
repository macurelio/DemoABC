package com.example.consumo.dao;

import com.example.consumo.domain.Consumo;
import com.example.consumo.domain.NotaCredito;
import com.example.consumo.domain.Valor;
import com.example.consumo.servicio.ConsumoServices;
import com.example.consumo.servicio.NotaCreditoRepository;
import com.example.consumo.servicio.ValorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotaCreditoService  {
    @Autowired
    private NotaCreditoRepository notaCreditoRepository;
    @Autowired
    private ValorImpl valorRepository;

    public NotaCredito calcularValoresNC(Long folio, Consumo consumo) {
    NotaCredito notaCredito = notaCreditoRepository.findByConsumo_Folio(folio);
        NotaCredito notaDeCredito = new NotaCredito();
        LocalDate lecturaAnt1 = consumo.getLecturaAnt1();
        LocalDate lecturaAct2 = consumo.getLecturaAct2();

        double getDiasTranscurridos1 = consumo.getDiasTranscurridos1();
        double getDiasTranscurridos2 = consumo.getDiasTranscurridos2();

        double getPorentaje1 = (getDiasTranscurridos1 / consumo.getDias()) * 100.0;
        double getPorentaje2 = (getDiasTranscurridos2 / consumo.getDias()) * 100.0;

        double getPorcentajePrecio1 = (getPorentaje1 * consumo.getConsumoKwh()) / 100;
        double getPorcentajePrecio2 = (getPorentaje2 * consumo.getConsumoKwh()) / 100;
        double getPorcentajeArriendo1 = (getPorentaje1 * 1) / 100;
        double getPorcentajeArriendo2 = (getPorentaje2 * 1) / 100;

        // Obtener el valor por el período desde lecturaAnt1 hasta fin de mes
        List<Valor> valoresLecturaAnt1 = valorRepository.obtenerValorPorPeriodo(lecturaAnt1, lecturaAnt1);

        if (!valoresLecturaAnt1.isEmpty()) {
            double totalAdmin1 = 0.0;
            double totalTransElect1 = 0.0;
            double totalConsumoTotal1 = 0.0;
            double totalArriendo1 = 0.0;
            double cargoCalculado1 = 0.0;
            double compra1 = 0.0;
            double tpobase1 = 0.0;

            for (Valor valor : valoresLecturaAnt1) {
                totalAdmin1 += valor.getPrcAdminservicio();
                totalTransElect1 += valor.getPrcElec();
                totalConsumoTotal1 += valor.getPrcCargoener();

                totalArriendo1 += valor.getPrcArriendo();
                cargoCalculado1 += valor.getPrcCargo();
                compra1 += valor.getPrcCompra();
                tpobase1 += valor.getPrcTpobase();
            }
            double getTotalAdmin1 = totalAdmin1 * getPorcentajeArriendo1 ;
            double getTotalTransElec1 = totalTransElect1 * getPorcentajePrecio1 ;
            double getTotalConsumoTotal1 = totalConsumoTotal1 * getPorcentajePrecio1 ;
            double getTotalArriendo1 = totalArriendo1 * getPorcentajeArriendo1 ;
            double getCargoCalculado1 = cargoCalculado1 * getPorcentajePrecio1 ;
            double getTotalCompra1 = compra1 * getPorcentajePrecio1 ;
            double getTpoBase1 = tpobase1 * getPorcentajePrecio1 ;


            consumo.setAdminServicio(getTotalAdmin1);
            consumo.setTransElect(getTotalTransElec1);
            consumo.setConsumoTotal(getTotalConsumoTotal1);
            consumo.setArriendo(getTotalArriendo1);
            consumo.setCargoEner(getCargoCalculado1);
            consumo.setCompra(getTotalCompra1);
            consumo.setTpobase(getTpoBase1);
        } else {
            throw new RuntimeException("No se encontró un precio válido para lecturaAnt1");
        }

        List<Valor> valoresLecturaAct2 = valorRepository.obtenerValorPorPeriodo(lecturaAct2, lecturaAct2);

        if (!valoresLecturaAct2.isEmpty()) {
            double totalAdmin2 = 0.0;
            double totalTransElect2 = 0.0;
            double totalConsumoTotal2 = 0.0;
            double totalArriendo2 = 0.0;
            double cargoCalculado2 = 0.0;
            double compra2 = 0.0;
            double tpobase2 = 0.0;

            for (Valor valor : valoresLecturaAct2) {
                totalAdmin2 += valor.getPrcAdminservicio();
                totalTransElect2 += valor.getPrcElec();

                totalConsumoTotal2 += valor.getPrcCargoener();
                totalArriendo2 += valor.getPrcArriendo();
                cargoCalculado2 += valor.getPrcCargo();
                compra2 += valor.getPrcCompra();
                tpobase2 += valor.getPrcTpobase();

            }
            double getTotalAdmin2 = totalAdmin2 * getPorcentajeArriendo2 ;
            double getTotalTransElec2 = totalTransElect2 * getPorcentajePrecio2 ;
            double getTotalConsumoTotal2 = totalConsumoTotal2 * getPorcentajePrecio2 ;
            double getTotalArriendo2 = totalArriendo2 * getPorcentajeArriendo2 ;
            double getCargoCalculado2 = cargoCalculado2 * getPorcentajePrecio2 ;
            double getTotalCompra2 = compra2 * getPorcentajePrecio2 ;
            double getTpoBase2 = tpobase2 * getPorcentajePrecio2 ;



            consumo.setAdminServicio2(getTotalAdmin2);
            consumo.setTransElect2(getTotalTransElec2);
            consumo.setConsumoTotal2(getTotalConsumoTotal2);
            consumo.setArriendo2(getTotalArriendo2);
            consumo.setCargoEner2(getCargoCalculado2);
            consumo.setCompra2(getTotalCompra2);
            consumo.setTpobase2(getTpoBase2);
        } else {
            throw new RuntimeException("No se encontró un precio válido para lecturaAct2");
        }

        double totalAmin = (consumo.getAdminServicio() + consumo.getAdminServicio2()) * 1.19;
        double totalElec = (consumo.getTransElect() + consumo.getTransElect2()) * 1.19 ;
        double totalConsumo = (consumo.getConsumoTotal() + consumo.getConsumoTotal2() ) * 1.19;
        double totalCompra = (consumo.getCompra() + consumo.getCompra2()) *1.19;
        double totalTpo = (consumo.getTpobase() + consumo.getTpobase2()) * 1.19;
        double totalArriendo = (consumo.getArriendo() + consumo.getArriendo2()) *1.19;
        double excento = (consumo.getCargoEner() + consumo.getCargoEner2());

        double getNeto = totalAmin + totalElec + totalArriendo + totalConsumo + totalCompra + totalTpo + excento;
        double getImpuesto = getNeto * 0.19;
        double getTotal = notaDeCredito.getMntoAfecto() + notaDeCredito.getMntoAfecto() ;


        notaCredito.setMntoAfecto(getNeto);
        notaCredito.setArriendo(totalArriendo);
        notaCredito.setAdminServicio(totalAmin);
        notaCredito.setMntoNoafecto(excento);
        notaCredito.setConsumoTotal(totalCompra);
       notaCredito.setTransElect(totalElec);
        notaCredito.setIva(getImpuesto);
        notaCredito.setTotal(getTotal);

        return notaCreditoRepository.save(notaDeCredito);
    }

    public NotaCredito crearNotaDeCredito(Consumo consumo, Double adminServicio,
                                            Double transElect,
                                            Double consumoTotal,
                                            Double arriendo,
                                            Double tpoBase) {
        NotaCredito notaDeCredito = new NotaCredito();
        //notaDeCredito.setMntoAfecto(nuevoKilowattsConsumidos);
        notaDeCredito.setTransElect(transElect);
        notaDeCredito.setAdminServicio(adminServicio);
        notaDeCredito.setArriendo(arriendo);
        notaDeCredito.setConsumoTotal(consumoTotal); // Vincula la nota de crédito con el consumo original

        Double getMntAfecto = transElect + adminServicio + arriendo + consumoTotal;
        Double getMntNoAfecto = tpoBase;

        return notaCreditoRepository.save(notaDeCredito);
    }

}








