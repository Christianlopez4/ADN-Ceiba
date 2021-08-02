package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;

import java.time.LocalDate;

public class ServicioCancelarCita {

    private RepositorioCita repositorioCita;

    private DaoPaciente daoPaciente;

    private DaoCategoria daoCategoria;

    private DaoCita daoCita;

    public ServicioCancelarCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, DaoCita daoCita) {
        this.repositorioCita = repositorioCita;
        this.daoPaciente = daoPaciente;
        this.daoCategoria = daoCategoria;
        this.daoCita = daoCita;
    }

    public void ejecutar(Integer id) {
        DtoCita dtocita = this.daoCita.buscar(id);
        Cita cita = this.toCita(dtocita);
        Double costoCancelacion = this.calcularCostoCancelacion(cita);
        cita.setCosto(costoCancelacion);
        cita.setEstado("CANCELADA");
        this.repositorioCita.actualizar(cita);
    }

    private Double calcularCostoCancelacion(Cita cita) {
        Double costoCancelacion;

        LocalDate diaActual = LocalDate.now();

        if (diaActual.compareTo(cita.getFecha()) == 0) {
            Long idPaciente = cita.getIdPaciente();
            DtoPaciente dtoPaciente = this.daoPaciente.buscar(idPaciente);

            Integer idCategoria = dtoPaciente.getIdCategoria();
            DtoCategoria dtoCategoria = this.daoCategoria.buscar(idCategoria);

            costoCancelacion = dtoCategoria.getMulta();
        } else {
            costoCancelacion = 0.0;
        }

        return costoCancelacion;
    }

    private Cita toCita(DtoCita dtoCita) {
        return new Cita(dtoCita.getId(), dtoCita.getFecha(), dtoCita.getHora(), dtoCita.getCosto(), dtoCita.getIdPaciente(), dtoCita.getEstado());
    }
}
