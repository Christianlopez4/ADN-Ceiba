package com.ceiba.paciente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorCrearPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class ComandoControladorPaciente {

    @Autowired
    private ManejadorCrearPaciente manejadorCrearPaciente;

    @PostMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<ComandoRespuesta<Long>> crear(@RequestBody ComandoPaciente comandoPaciente) {
        try {
            return new ResponseEntity<>(manejadorCrearPaciente.ejecutar(comandoPaciente), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
