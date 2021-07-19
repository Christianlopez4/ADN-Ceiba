package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class DtoCita {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;
}
