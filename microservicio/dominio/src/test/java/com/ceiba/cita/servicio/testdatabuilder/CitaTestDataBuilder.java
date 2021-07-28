package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitaTestDataBuilder {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public CitaTestDataBuilder() {
        this.id = 1;
        this.hora = LocalTime.of(14, 00, 00);
        this.costo = 3000.0;
        this.idPaciente = 123L;
        this.estado = "ACTIVA";
    }

    public CitaTestDataBuilder conFecha(LocalDate fecha) {
        this.fecha = fecha;
        return this;
    }

    public CitaTestDataBuilder conEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public CitaTestDataBuilder conId(Integer id) {
        this.id = id;
        return this;
    }

    public CitaTestDataBuilder conCosto(Double costo) {
        this.costo = costo;
        return this;
    }

    public Cita build() {
        return new Cita(id, fecha, hora, costo, idPaciente, estado);
    }

    public DtoCita buildDto() {
        return new DtoCita(id, fecha, hora, costo, idPaciente, estado);
    }
}
