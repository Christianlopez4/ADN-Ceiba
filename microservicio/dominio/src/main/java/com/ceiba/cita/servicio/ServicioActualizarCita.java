package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;

public class ServicioActualizarCita {

    private RepositorioCita repositorioCita;

    private DaoPaciente daoPaciente;

    private DaoCategoria daoCategoria;

    private DaoCita daoCita;

    private ServicioCita servicioCita;

    public ServicioActualizarCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, DaoCita daoCita, ServicioCita servicioCita) {
        this.repositorioCita = repositorioCita;
        this.daoPaciente = daoPaciente;
        this.daoCategoria = daoCategoria;
        this.daoCita = daoCita;
        this.servicioCita = servicioCita;
    }

    public void ejecutar(Cita cita) {
        if (cita.esCancelacion()) {
            calcularCostoCancelacion(cita);
        } else {
            cita = servicioCita.validarFestivo(cita);
            cita = servicioCita.validarMultipleCitaElMismoDia(cita);
        }

        this.repositorioCita.actualizar(cita);
    }

    public void calcularCostoCancelacion(Cita cita) {
        Double costoCancelacion;
        DtoCita dtoCita = this.daoCita.buscar(cita.getId());
        if (cita.getFecha().compareTo(dtoCita.getFecha()) == 0) {
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

}
