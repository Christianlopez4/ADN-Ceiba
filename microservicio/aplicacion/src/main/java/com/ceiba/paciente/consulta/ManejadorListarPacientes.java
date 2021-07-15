package com.ceiba.paciente.consulta;

import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPacientes {

    @Autowired
    private DaoPaciente daoPaciente;

    public List<DtoPaciente> listar(){
        return daoPaciente.listar();
    }
}
