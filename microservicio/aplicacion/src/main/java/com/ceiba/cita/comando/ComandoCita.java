package com.ceiba.cita.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoCita {

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;
}
