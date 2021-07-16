package com.ceiba.cita.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;
import java.sql.Time;

@Getter
@AllArgsConstructor
public class DtoCita {

    private Long id;
    private Date fecha;
    private Time hora;
    private Double costo;
    private Long idPaciente;
    private Integer idEstado;
}
