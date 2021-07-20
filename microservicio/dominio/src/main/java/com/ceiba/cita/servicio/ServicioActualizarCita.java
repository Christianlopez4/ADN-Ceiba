package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.utils.HolidayUtil;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;

import java.time.LocalDate;


public class ServicioActualizarCita {

    public static final String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private RepositorioCita repositorioCita;

    private HolidayUtil holidayUtil;

    private DaoPaciente daoPaciente;

    private DaoCategoria daoCategoria;

    private DaoCita daoCita;

    public ServicioActualizarCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoPaciente = daoPaciente;
        this.daoCategoria = daoCategoria;
        this.daoCita = daoCita;
        this.holidayUtil = new HolidayUtil(LocalDate.now().getYear());
    }

    public void ejecutar(Cita cita) {
        if (cita.esCancelacion()) {
            validarCostoCancelacion(cita);
        } else {
            validarFestivo(cita);
            validarMultipleCitaElMismoDia(cita);
        }

        this.repositorioCita.actualizar(cita);
    }

    public void validarCostoCancelacion(Cita cita) {
        Double costoCancelacion;
        DtoCita dtoCita = this.daoCita.buscar(cita.getId());
        if (LocalDate.now().equals(dtoCita.getFecha())) {
            Long idPaciente = cita.getIdPaciente();
            DtoPaciente dtoPaciente = this.daoPaciente.buscar(idPaciente);

            Integer idCategoria = dtoPaciente.getIdCategoria();
            DtoCategoria dtoCategoria = this.daoCategoria.buscar(idCategoria);

            costoCancelacion = dtoCategoria.getMulta();
        } else {
            costoCancelacion = 0.0;
        }
        cita.setCosto(costoCancelacion);
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
