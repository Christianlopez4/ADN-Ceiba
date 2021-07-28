package com.ceiba.cita.servicio;

import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.utils.HolidayUtil;

import java.time.LocalDate;

public class ServicioCita {

    private RepositorioCita repositorioCita;

    private HolidayUtil holidayUtil;

    public ServicioCita(RepositorioCita repositorioCita) {
        this.repositorioCita = repositorioCita;
        this.holidayUtil = new HolidayUtil(LocalDate.now().getYear());
    }

    public Cita validarFestivo(Cita cita) {
        LocalDate fecha = cita.getFecha();
        int dia = fecha.getDayOfMonth();
        int mes = fecha.getMonthValue();
        Boolean esFestivo = this.holidayUtil.isHoliday(mes, dia);
        if (esFestivo) {
            cita.setCosto(cita.getCosto() * 2);
        }
        return cita;
    }

    public Cita validarMultipleCitaElMismoDia(Cita cita) throws ExcepcionMultipleCitaElMismoDia {
        boolean esMultiple = this.repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha());
        if (esMultiple) {
            throw new ExcepcionMultipleCitaElMismoDia(Cita.MENSAJE_MULTIPLE_CITA);
        }
        return cita;
    }
}
