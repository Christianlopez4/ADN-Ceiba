package com.ceiba.paciente.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.fabrica.FabricaPaciente;
import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.servicio.ServicioCrearPaciente;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPaciente implements ManejadorComandoRespuesta<ComandoPaciente, ComandoRespuesta<Long>> {

    private FabricaPaciente fabricaPaciente;
    private ServicioCrearPaciente servicioCrearPaciente;

    public ManejadorCrearPaciente(FabricaPaciente fabricaPaciente, ServicioCrearPaciente servicioCrearPaciente) {
        this.fabricaPaciente = fabricaPaciente;
        this.servicioCrearPaciente = servicioCrearPaciente;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoPaciente comando) {
        Paciente paciente = this.fabricaPaciente.crear(comando);
        return new ComandoRespuesta<>(this.servicioCrearPaciente.ejecutar(paciente));
    }
}
