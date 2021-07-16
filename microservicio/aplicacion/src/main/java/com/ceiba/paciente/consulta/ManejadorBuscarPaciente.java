package com.ceiba.paciente.consulta;

import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBuscarPaciente {

    private DaoPaciente daoPaciente;

    public ManejadorBuscarPaciente(DaoPaciente daoPaciente) {
        this.daoPaciente = daoPaciente;
    }

    public DtoPaciente buscar(Long id) {
        return this.daoPaciente.buscar(id);
    }
}
