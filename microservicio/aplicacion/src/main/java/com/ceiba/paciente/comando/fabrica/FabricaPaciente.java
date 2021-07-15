package com.ceiba.paciente.comando.fabrica;

import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.modelo.entidad.Paciente;
import org.springframework.stereotype.Component;

@Component
public class FabricaPaciente {
    public Paciente crear(ComandoPaciente comandoPaciente) {
        return new Paciente(
                comandoPaciente.getId(),
                comandoPaciente.getNombre(),
                comandoPaciente.getApellidos(),
                comandoPaciente.getFechaNacimiento(),
                comandoPaciente.getCorreoElectronico(),
                comandoPaciente.getTelefono(),
                comandoPaciente.getIdCategoria(),
                comandoPaciente.getIdDocumento()
        );
    }
}
