package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.comando.ComandoCita;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComandoCitaTestDataBuilder {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public ComandoCitaTestDataBuilder() {
        this.id = 1;
        this.fecha = LocalDate.of(2021,7,29);
        this.hora = LocalTime.of(14,0,0);
        this.costo = 3000.0;
        this.idPaciente = 2L;
        this.estado = "ACTIVA";
    }

    public ComandoCitaTestDataBuilder conIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
        return this;
    }


    public ComandoCita build() {
        return new ComandoCita(id, fecha, hora, costo, idPaciente, estado);
    }
}
