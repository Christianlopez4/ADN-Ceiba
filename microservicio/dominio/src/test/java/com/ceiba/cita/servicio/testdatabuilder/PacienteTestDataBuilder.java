package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.paciente.modelo.dto.DtoPaciente;

import java.sql.Date;

public class PacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

    public PacienteTestDataBuilder() {
        this.id = 123L;
        this.nombre = "Christian";
        this.apellidos = "Lopez Cleves";
        this.fechaNacimiento = new Date(1999,5,26);
        this.correoElectronico = "christian@correo.com";
        this.telefono = 123456L;
        this.idCategoria = 1;
        this.idDocumento = 1;
    }

    public PacienteTestDataBuilder conIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public DtoPaciente build() {
        return new DtoPaciente(id, nombre, apellidos, fechaNacimiento, correoElectronico, telefono, idCategoria, idDocumento);
    }
}
