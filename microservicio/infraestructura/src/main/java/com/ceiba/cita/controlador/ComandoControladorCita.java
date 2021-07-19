package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("citas")
public class ComandoControladorCita {

    @Autowired
    private ManejadorCrearCita manejadorCrearCita;

    @Autowired
    private ManejadorActualizarCita manejadorActualizarCita;

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoCita comandoCita) {
        return manejadorCrearCita.ejecutar(comandoCita);
    }

    @PutMapping(value="/{id}")
    public void actualizar(@RequestBody ComandoCita comandoCita, @PathVariable Integer id) {
        comandoCita.setId(id);
        manejadorActualizarCita.ejecutar(comandoCita);
    }
}
