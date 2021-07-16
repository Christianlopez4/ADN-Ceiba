package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServicioCrearCita {

    private final static String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";

    private RepositorioCita repositorioCita;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
    }

    public Long ejecutar(Cita cita) {
        System.out.println(cita.getFecha());
        return this.repositorioCita.crear(cita);
    }


    private void validarDia(Cita cita) throws ExcepcionDiaInvalido {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(cita.getFecha());

        int diaSemana = g.get(Calendar.DAY_OF_WEEK);

        if (diaSemana == 1 || diaSemana == 7) {
            throw new ExcepcionDiaInvalido("MENSAJE_DIA_INVALIDO");
        }

    }

    private void validarFestivo(Cita cita) {

    }

    private void validarMultipleCitaElMismoDia(Cita cita) {
        
    }
}
