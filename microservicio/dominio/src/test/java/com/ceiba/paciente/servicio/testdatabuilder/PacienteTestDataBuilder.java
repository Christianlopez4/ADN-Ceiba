package com.ceiba.paciente.servicio.testdatabuilder;

import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.modelo.entidad.Paciente;

import java.time.LocalDate;

public class PacienteTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

    public PacienteTestDataBuilder() {
        this.id = 1L;
        this.nombre = "Christian";
        this.apellidos = "Lopez Cleves";
        this.fechaNacimiento = LocalDate.of(1999, 5, 26);
        this.correoElectronico = "christian@correo.com";
        this.telefono = 123456L;
        this.idCategoria = 1;
        this.idDocumento = 1;
    }

    public PacienteTestDataBuilder conID(Long id) {
        this.id = id;
        return this;
    }

    public PacienteTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public PacienteTestDataBuilder conApellido(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

    public PacienteTestDataBuilder conCorreo(String correoElectronico) {
        this.correoElectronico = correoElectronico;
        return this;
    }

    public PacienteTestDataBuilder conIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
        return this;
    }

    public Paciente build() {
        return new Paciente(id, nombre, apellidos, fechaNacimiento, correoElectronico, telefono, idCategoria, idDocumento);
    }
}
