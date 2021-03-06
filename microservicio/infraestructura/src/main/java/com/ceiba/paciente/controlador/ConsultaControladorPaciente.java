package com.ceiba.paciente.controlador;

import com.ceiba.paciente.consulta.ManejadorBuscarPaciente;
import com.ceiba.paciente.consulta.ManejadorListarPacientes;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class ConsultaControladorPaciente {

    @Autowired
    private ManejadorListarPacientes manejadorListarPacientes;

    @Autowired
    private ManejadorBuscarPaciente manejadorBuscarPaciente;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<DtoPaciente>> listar() {
        return new ResponseEntity<>(manejadorListarPacientes.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoPaciente> buscar(@PathVariable Long id) {
        return new ResponseEntity<>(manejadorBuscarPaciente.buscar(id), HttpStatus.OK);
    }
}
