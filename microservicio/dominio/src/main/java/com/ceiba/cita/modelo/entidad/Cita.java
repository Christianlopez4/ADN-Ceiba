package com.ceiba.cita.modelo.entidad;

import lombok.Getter;

import java.sql.Date;
import java.sql.Time;

@Getter
public class Cita {

    private Long id;
    private Date fecha;
    private Time hora;
    private Double costo;
    private Long idPaciente;
    private Integer idEstado;

    public Cita(Long id, Date fecha, Time hora, Double costo, Long idPaciente, Integer idEstado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.idPaciente = idPaciente;
        this.idEstado = idEstado;
    }
}
