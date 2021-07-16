package com.ceiba.categoria.controlador;

import com.ceiba.categoria.consulta.ManejadorBuscarCategoria;
import com.ceiba.categoria.consulta.ManejadorListarCategorias;
import com.ceiba.categoria.modelo.dto.DtoCategoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("categorias")
public class ConsultaControladorCategoria {

    @Autowired
    private ManejadorListarCategorias manejadorListarCategorias;

    @Autowired
    private ManejadorBuscarCategoria manejadorBuscarCategoria;

    @GetMapping
    public ResponseEntity<List<DtoCategoria>> listar() {
        return new ResponseEntity<>(manejadorListarCategorias.ejecutar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DtoCategoria> buscar(@PathVariable Integer id) {
        return new ResponseEntity<>(manejadorBuscarCategoria.ejecutar(id), HttpStatus.OK);
    }
}
