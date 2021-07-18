package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.utils.HolidayUtil;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServicioCrearCita {

    private final static String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";
    private final static String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private RepositorioCita repositorioCita;

    private HolidayUtil holidayUtil;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
        this.holidayUtil = new HolidayUtil(LocalDate.now().getYear());
    }

    public Long ejecutar(Cita cita) {
        validarDia(cita);
        validarFestivo(cita);
        validarMultipleCitaElMismoDia(cita);
        return this.repositorioCita.crear(cita);
    }

    private void validarDia(Cita cita) {
        DayOfWeek diaSemana =  cita.getFecha().getDayOfWeek();

        if (diaSemana.equals(DayOfWeek.SATURDAY) || diaSemana.equals(DayOfWeek.SUNDAY)) {
            throw new ExcepcionDiaInvalido(MENSAJE_DIA_INVALIDO);
        }
    }

    private void validarFestivo(Cita cita) {
        LocalDate fecha = cita.getFecha();
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        Boolean esFestivo = this.holidayUtil.isHoliday(mes, dia);

        if (esFestivo) {
            cita.setCosto(cita.getCosto() * 2);
        }
    }

    private void validarMultipleCitaElMismoDia(Cita cita) throws ExcepcionMultipleCitaElMismoDia {
        boolean esMultiple = this.repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha());
        if (esMultiple) {
            throw new ExcepcionMultipleCitaElMismoDia(MENSAJE_MULTIPLE_CITA);
        }
    }
}
