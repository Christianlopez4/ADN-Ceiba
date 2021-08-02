package com.ceiba.cita.comando.manejador;

import com.ceiba.cita.comando.fabrica.FabricaCita;

import com.ceiba.cita.servicio.ServicioCancelarCita;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCancelarCita {

    private FabricaCita fabricaCita;

    private ServicioCancelarCita servicioCancelarCita;

    public ManejadorCancelarCita(FabricaCita fabricaCita, ServicioCancelarCita servicioCancelarCita) {
        this.fabricaCita = fabricaCita;
        this.servicioCancelarCita = servicioCancelarCita;
    }

    public void ejecutar(Integer id) {
        this.servicioCancelarCita.ejecutar(id);
    }
}
