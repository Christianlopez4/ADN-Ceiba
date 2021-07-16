package com.ceiba.cita.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Getter
@Setter
public class Cita {

    private Long id;
    private Date fecha;
    private Time hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public Cita(Long id, Date fecha, Time hora, Double costo, Long idPaciente, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.idPaciente = idPaciente;
        this.estado = estado;
    }
}
