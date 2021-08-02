package com.ceiba.cita.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.cita.comando.ComandoCita;
import com.ceiba.cita.comando.manejador.ManejadorActualizarCita;
import com.ceiba.cita.comando.manejador.ManejadorCancelarCita;
import com.ceiba.cita.comando.manejador.ManejadorCrearCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("citas")
public class ComandoControladorCita {

    @Autowired
    private ManejadorCrearCita manejadorCrearCita;

    @Autowired
    private ManejadorActualizarCita manejadorActualizarCita;

    @Autowired
    private ManejadorCancelarCita manejadorCancelarCita;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ComandoRespuesta<Long>> crear(@RequestBody ComandoCita comandoCita) {
        try {
            return new ResponseEntity<>(manejadorCrearCita.ejecutar(comandoCita), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value="/{id}")
    public void actualizar(@RequestBody ComandoCita comandoCita, @PathVariable Integer id) {
        comandoCita.setId(id);
        manejadorActualizarCita.ejecutar(comandoCita);
    }

    @PutMapping("/{id}/cancelar")
    public void cancelar(@PathVariable Integer id) {
        manejadorCancelarCita.ejecutar(id);
    }
}
