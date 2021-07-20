package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.paciente.modelo.dto.DtoPaciente;

import java.time.LocalDate;

public class DtoPacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

    public DtoPacienteTestDataBuilder() {
        this.id = 123L;
        this.nombre = "Christian";
        this.apellidos = "Lopez Cleves";
        this.fechaNacimiento = LocalDate.of(1999, 5, 26);
        this.correoElectronico = "christian@correo.com";
        this.telefono = 123456L;
        this.idCategoria = 1;
        this.idDocumento = 1;
    }

    public DtoPacienteTestDataBuilder conIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public DtoPaciente build() {
        return new DtoPaciente(id, nombre, apellidos, fechaNacimiento, correoElectronico, telefono, idCategoria, idDocumento);
    }
}
