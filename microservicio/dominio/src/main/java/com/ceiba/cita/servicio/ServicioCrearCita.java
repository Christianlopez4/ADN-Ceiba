package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.utils.HolidayUtil;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ServicioCrearCita {

    public final static String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private RepositorioCita repositorioCita;

    private HolidayUtil holidayUtil;

    private DaoPaciente daoPaciente;

    private DaoCategoria daoCategoria;

    public ServicioCrearCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria) {
        this.repositorioCita = repositorioCita;
        this.daoPaciente = daoPaciente;
        this.daoCategoria = daoCategoria;
        this.holidayUtil = new HolidayUtil(LocalDate.now().getYear());
    }

    public Long ejecutar(Cita cita) {
        calcularCosto(cita);
        validarFestivo(cita);
        validarMultipleCitaElMismoDia(cita);
        return this.repositorioCita.crear(cita);
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

    public void calcularCosto(Cita cita) {
        Long idPaciente = cita.getIdPaciente();
        DtoPaciente dtoPaciente = this.daoPaciente.buscar(idPaciente);

        Integer idCategoria = dtoPaciente.getIdCategoria();
        DtoCategoria dtoCategoria = this.daoCategoria.buscar(idCategoria);

        cita.setCosto(dtoCategoria.getCuotaModeradora());

    }
}
