package com.ceiba.cita.controlador;

import com.ceiba.cita.consulta.ManejadorBuscarCita;
import com.ceiba.cita.consulta.ManejadorListarCitas;
import com.ceiba.cita.modelo.dto.DtoCita;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("citas")
public class ConsultaControladorCita {

    @Autowired
    private ManejadorListarCitas manejadorListarCitas;

    @Autowired
    private ManejadorBuscarCita manejadorBuscarCita;

    @GetMapping
    public ResponseEntity<List<DtoCita>> listar() {
        return new ResponseEntity<>(manejadorListarCitas.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoCita> buscar(@PathVariable Integer id) {
        return new ResponseEntity<>(manejadorBuscarCita.buscar(id), HttpStatus.OK);
    }
}
