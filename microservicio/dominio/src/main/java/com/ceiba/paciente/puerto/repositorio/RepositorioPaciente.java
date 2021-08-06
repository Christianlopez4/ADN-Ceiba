package com.ceiba.paciente.puerto.repositorio;

import com.ceiba.paciente.modelo.entidad.Paciente;

public interface RepositorioPaciente {

    public Long crear(Paciente paciente);

    public boolean existePorId(Long id);
}
