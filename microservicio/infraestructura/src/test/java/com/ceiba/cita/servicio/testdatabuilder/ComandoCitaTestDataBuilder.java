package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.comando.ComandoCita;

import java.time.LocalDate;
import java.time.LocalTime;

public class ComandoCitaTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public ComandoCitaTestDataBuilder() {
        this.fecha = LocalDate.of(2021,7,19);
        this.hora = LocalTime.of(14,0,0);
        this.idPaciente = 123L;
        this.estado = "ACTIVA";
    }

    public ComandoCita build() {
        return new ComandoCita(id, fecha, hora, costo, idPaciente, estado);
    }
}
