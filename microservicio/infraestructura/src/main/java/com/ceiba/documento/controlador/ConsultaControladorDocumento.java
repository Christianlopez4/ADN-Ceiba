package com.ceiba.documento.controlador;

import com.ceiba.documento.consulta.ManejadorListarDocumentos;
import com.ceiba.documento.modelo.dto.DtoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("documentos")
public class ConsultaControladorDocumento {

    @Autowired
    private ManejadorListarDocumentos manejadorListarDocumentos;

    @GetMapping
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<List<DtoDocumento>> listar() {
        return new ResponseEntity<>(manejadorListarDocumentos.listar(), HttpStatus.OK);
    }
}
