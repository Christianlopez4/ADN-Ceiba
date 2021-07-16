package com.ceiba.paciente.puerto.dao;

import com.ceiba.paciente.modelo.dto.DtoPaciente;

import java.util.List;

public interface DaoPaciente {

    List<DtoPaciente> listar();

    DtoPaciente buscar(Long id);
}
