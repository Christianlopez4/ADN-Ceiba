package com.ceiba.paciente.modelo.entidad;

import com.ceiba.dominio.ValidadorArgumento;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import lombok.Getter;
import java.time.LocalDate;

@Getter
public class Paciente {

    public static final String ID_OBLIGATORIO = "El ID es un campo obligatorio";
    public static final String NOMBRE_OBLIGATORIO = "El NOMBRE es un campo obligatorio";
    public static final String APELLIDOS_OBLIGATORIO = "El APELLIDO es un campo obligatorio";
    public static final String FECHA_OBLIGATORIO = "La FECHA DE NACIMIENTO es un campo obligatorio";
    public static final String CORREO_OBLIGATORIO = "El CORREO es un campo obligatorio";
    public static final String TELEFONO_OBLIGATORIO = "El TELEFONO es un campo obligatorio";
    public static final String CATEGORIA_OBLIGATORIO = "La CATEGORIA es un campo obligatorio";
    public static final String DOCUMENTO_OBLIGATORIO = "El DOCUMENTO es un campo obligatorio";

    public static final String ID_POSITIVO = "El ID no puede ser un campo negativo";
    public static final String NOMBRE_NO_VACIO = "El NOMBRE no puede ser un campo vacío";
    public static final String APELLIDOS_NO_VACIO = "El NOMBRE no puede ser un campo vacío";
    public static final String CORREO_NO_VACIO = "El NOMBRE no puede ser un campo vacío";

    private Long id;
    private String nombre;
    private String apellidos;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private Long telefono;
    private Integer idCategoria;
    private Integer idDocumento;

    public Paciente(Long id, String nombre, String apellidos, LocalDate fechaNacimiento, String correoElectronico, Long telefono, Integer idCategoria, Integer idDocumento) {
        ValidadorArgumento.validarObligatorio(id, ID_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(nombre, NOMBRE_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(apellidos, APELLIDOS_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(fechaNacimiento, FECHA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(correoElectronico, CORREO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(telefono, TELEFONO_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(idCategoria, CATEGORIA_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(idDocumento, DOCUMENTO_OBLIGATORIO);
        this.validarIdPositivo(id, ID_POSITIVO);
        this.validarVacio(nombre, NOMBRE_NO_VACIO);
        this.validarVacio(apellidos, APELLIDOS_NO_VACIO);
        this.validarVacio(correoElectronico, CORREO_NO_VACIO);

        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.telefono = telefono;
        this.idCategoria = idCategoria;
        this.idDocumento = idDocumento;
    }

    private void validarIdPositivo(Long id, String mensaje) {
        if (id <= 0) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    private void validarVacio(String valor, String mensaje) {
        valor = valor.trim();
        if (valor.isEmpty()) {
            throw new ExcepcionSinDatos(mensaje);
        }
    }

}
