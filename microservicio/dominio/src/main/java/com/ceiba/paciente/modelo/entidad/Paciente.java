package com.ceiba.paciente.modelo.entidad;

import lombok.Getter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
public class Paciente {

    private Long id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

    public Paciente(Long id, String nombre, String apellidos, LocalDate fechaNacimiento, String correoElectronico, Long telefono, Integer idCategoria, Integer idDocumento) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.idCategoria = idCategoria;
        this.idDocumento = idDocumento;
    }
}
