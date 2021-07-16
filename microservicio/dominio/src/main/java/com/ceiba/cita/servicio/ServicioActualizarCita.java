package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServicioActualizarCita {

    private final static String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";
    private final static String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private RepositorioCita repositorioCita;

    public ServicioActualizarCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public void ejecutar(Cita cita) {
        this.repositorioCita.actualizar(cita);
    }

}
