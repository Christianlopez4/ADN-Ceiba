package com.ceiba.cita.modelo.entidad;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class Cita {

    private Long id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public Cita(Long id, LocalDate fecha, LocalTime hora, Double costo, Long idPaciente, String estado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.idPaciente = idPaciente;
        this.estado = estado;
    }
}
