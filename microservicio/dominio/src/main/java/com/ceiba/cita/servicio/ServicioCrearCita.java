package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;

public class ServicioCrearCita {

    private RepositorioCita repositorioCita;

    private DaoPaciente daoPaciente;

    private DaoCategoria daoCategoria;

    private ServicioCita servicioCita;

    public ServicioCrearCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, ServicioCita servicioCita) {
        this.repositorioCita = repositorioCita;
        this.daoPaciente = daoPaciente;
        this.daoCategoria = daoCategoria;
        this.servicioCita = servicioCita;
    }

    public Long ejecutar(Cita cita) {
        this.calcularCosto(cita);
        cita = servicioCita.validarFestivo(cita);
        cita = servicioCita.validarMultipleCitaElMismoDia(cita);
        return this.repositorioCita.crear(cita);
    }

    public void calcularCosto(Cita cita) {
        Long idPaciente = cita.getIdPaciente();
        DtoPaciente dtoPaciente = this.daoPaciente.buscar(idPaciente);

        Integer idCategoria = dtoPaciente.getIdCategoria();
        DtoCategoria dtoCategoria = this.daoCategoria.buscar(idCategoria);

        cita.setCosto(dtoCategoria.getCuotaModeradora());
    }
}
