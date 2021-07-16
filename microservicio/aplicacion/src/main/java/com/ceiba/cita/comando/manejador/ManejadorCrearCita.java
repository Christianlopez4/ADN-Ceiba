package com.ceiba.cita.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.fabrica.FabricaCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearCita implements ManejadorComandoRespuesta<ComandoCita, ComandoRespuesta<Long>> {

    private FabricaCita fabricaCita;
    private ServicioCrearCita servicioCrearCita;

    @Autowired
    private DaoPaciente daoPaciente;

    @Autowired
    private DaoCategoria daoCategoria;

    public ManejadorCrearCita(FabricaCita fabricaCita, ServicioCrearCita servicioCrearCita) {
        this.fabricaCita = fabricaCita;
        this.servicioCrearCita = servicioCrearCita;
    }

    @Override
    public ComandoRespuesta<Long> ejecutar(ComandoCita comando) {
        Long idPaciente = comando.getIdPaciente();
        DtoPaciente dtoPaciente = this.daoPaciente.buscar(idPaciente);

        Integer idCategoria = dtoPaciente.getIdCategoria();
        DtoCategoria dtoCategoria = this.daoCategoria.buscar(idCategoria);

        comando.setCosto(dtoCategoria.getCuotaModeradora());

        Cita cita = this.fabricaCita.crear(comando);
        return new ComandoRespuesta<>(this.servicioCrearCita.ejecutar(cita));
    }
}
