package com.ceiba.paciente.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.paciente.comando.ComandoPaciente;
import com.ceiba.paciente.comando.manejador.ManejadorCrearPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class ComandoControladorPaciente {

    @Autowired
    private ManejadorCrearPaciente manejadorCrearPaciente;

    @PostMapping
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPaciente comandoPaciente) {
        return manejadorCrearPaciente.ejecutar(comandoPaciente);
    }
}
