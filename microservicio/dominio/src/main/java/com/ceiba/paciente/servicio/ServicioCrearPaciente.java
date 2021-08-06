package com.ceiba.paciente.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;

public class ServicioCrearPaciente {

    public static final String PACIENTE_EXISTENTE ="El id ya se encuentra registrado";

    private final RepositorioPaciente repositorioPaciente;

    public ServicioCrearPaciente(RepositorioPaciente repositorioPaciente) {
        this.repositorioPaciente = repositorioPaciente;
    }

    public Long ejecutar(Paciente paciente) {
        validarExistenciaPrevia(paciente);
        return this.repositorioPaciente.crear(paciente);
    }

    private  void validarExistenciaPrevia(Paciente paciente) {
        boolean existe = this.repositorioPaciente.existePorId(paciente.getId());
        if (existe) {
            throw new ExcepcionDuplicidad(PACIENTE_EXISTENTE);
        }
    }
}
