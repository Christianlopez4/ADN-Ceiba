package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

public class ServicioActualizarCita {

    private RepositorioCita repositorioCita;

    private ServicioCita servicioCita;

    public ServicioActualizarCita(RepositorioCita repositorioCita, ServicioCita servicioCita) {
        this.repositorioCita = repositorioCita;
        this.servicioCita = servicioCita;
    }

    public void ejecutar(Cita cita) {
        servicioCita.validarFestivo(cita);
        servicioCita.validarMultipleCitaElMismoDia(cita);
        this.repositorioCita.actualizar(cita);
    }

}
