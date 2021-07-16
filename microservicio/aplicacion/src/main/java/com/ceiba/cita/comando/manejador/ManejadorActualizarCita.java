package com.ceiba.cita.comando.manejador;

import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.servicio.ServicioActualizarCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarCita {

    private FabricaCita fabricaCita;
    private ServicioActualizarCita servicioActualizarCita;

    @Autowired
    private DaoCita daoCita;

    public ManejadorActualizarCita(FabricaCita fabricaCita, ServicioActualizarCita servicioActualizarCita) {
        this.fabricaCita = fabricaCita;
        this.servicioActualizarCita = servicioActualizarCita;
    }

    public void ejecutar(ComandoCita comandoCita) {
        Cita cita = this.fabricaCita.crear(comandoCita);
        this.servicioActualizarCita.ejecutar(cita);
    }
}
