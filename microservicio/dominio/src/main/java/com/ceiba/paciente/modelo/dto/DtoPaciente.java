package com.ceiba.paciente.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Date;

@Getter
@AllArgsConstructor
public class DtoPaciente {

    private Long id;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

}
