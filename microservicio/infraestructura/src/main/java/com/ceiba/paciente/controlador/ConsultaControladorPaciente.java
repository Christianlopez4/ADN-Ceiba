package com.ceiba.paciente.controlador;

import com.ceiba.paciente.consulta.ManejadorListarPacientes;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("pacientes")
public class ConsultaControladorPaciente {

    @Autowired
    private ManejadorListarPacientes manejadorListarPacientes;

    @GetMapping
    public ResponseEntity<List<DtoPaciente>> listar() {
        return new ResponseEntity<>(manejadorListarPacientes.listar(), HttpStatus.OK);
    }
}