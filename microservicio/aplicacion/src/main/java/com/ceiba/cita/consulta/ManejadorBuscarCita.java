package com.ceiba.cita.consulta;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.puerto.dao.DaoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBuscarCita {

    @Autowired
    private DaoCita daoCita;

    public DtoCita buscar(Integer id) {
        return daoCita.buscar(id);
    }
}
