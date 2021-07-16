package com.ceiba.cita.puerto.repositorio;

import com.ceiba.cita.modelo.entidad.Cita;

import java.sql.Date;

public interface RepositorioCita {

    Long crear(Cita cita);

    Boolean existeMultipleCita(Long idPaciente, Date fecha);
}
