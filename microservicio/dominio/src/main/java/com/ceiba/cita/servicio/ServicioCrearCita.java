package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.utils.HolidayUtil;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServicioCrearCita {

    private final static String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";
    private final static String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private RepositorioCita repositorioCita;

    private HolidayUtil holidayUtil;

    public ServicioCrearCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
        this.holidayUtil = new HolidayUtil(2021);
    }

    public Long ejecutar(Cita cita) {
        validarDia(cita);
        validarFestivo(cita);
        validarMultipleCitaElMismoDia(cita);
        return this.repositorioCita.crear(cita);
    }

    private void validarDia(Cita cita) {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(cita.getFecha());

        int diaSemana = g.get(Calendar.DAY_OF_WEEK);

        if (diaSemana == 1 || diaSemana == 7) {
            throw new ExcepcionDiaInvalido("MENSAJE_DIA_INVALIDO");
        }

    }

    private void validarFestivo(Cita cita) {
        Integer dia = cita.getFecha().getDay();
        Integer mes = cita.getFecha().getMonth();
        Boolean festivo = this.holidayUtil.isHoliday(mes, dia);
        if (festivo) {
            cita.setCosto(cita.getCosto() * 2);
        }
    }

    private void validarMultipleCitaElMismoDia(Cita cita) throws ExcepcionMultipleCitaElMismoDia {
        boolean existe = this.repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha());
        if (existe) {
            throw new ExcepcionMultipleCitaElMismoDia("MENSAJE_MULTIPLE_CITA");
        }
    }
}
