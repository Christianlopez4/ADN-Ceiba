package com.ceiba.cita.puerto.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;

import java.util.List;

public interface DaoCita {

    List<DtoCita> listar();

    DtoCita buscar(Integer id);
}
