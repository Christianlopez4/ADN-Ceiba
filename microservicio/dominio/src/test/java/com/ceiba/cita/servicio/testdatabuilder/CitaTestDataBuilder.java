package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaTestDataBuilder {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public CitaTestDataBuilder() {
        this.id = 1L;
        this.hora = LocalTime.of(14, 00, 00);
        this.costo = 3000.0;
        this.idPaciente = 123L;
        this.estado = "ACTIVA";
    }

    public CitaTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public Cita build() {
        return new Cita(id, fecha, hora, costo, idPaciente, estado);
    }
}
