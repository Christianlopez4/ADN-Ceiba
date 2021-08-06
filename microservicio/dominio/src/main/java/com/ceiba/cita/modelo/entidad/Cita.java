package com.ceiba.cita.modelo.entidad;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.dominio.ValidadorArgumento;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class Cita {

    public static final String FECHA_OBLIGATORIA = "La FECHA es un campo obligatorio";
    public static final String HORA_OBLIGATORIA = "La HORA es un campo obligatorio";
    public static final String PACIENTE_OBLIGATORIO = "El PACIENTE es un campo obligatorio";
    public static final String ESTADO_OBLIGATORIO = "El ESTADO es un campo obligatorio";
    public static final String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";
    public static final String MENSAJE_MULTIPLE_CITA = "No es posible agendar más de una cita el mismo día";

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public Cita(Integer id, LocalDate fecha, LocalTime hora, Double costo, Long idPaciente, String estado) {
        ValidadorArgumento.validarObligatorio(fecha, FECHA_OBLIGATORIA);
        ValidadorArgumento.validarObligatorio(hora, HORA_OBLIGATORIA);
        ValidadorArgumento.validarObligatorio(idPaciente, PACIENTE_OBLIGATORIO);
        ValidadorArgumento.validarObligatorio(estado, ESTADO_OBLIGATORIO);
        validarDia(fecha);
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.costo = costo;
        this.idPaciente = idPaciente;
        this.estado = estado;
    }

    private void validarDia(LocalDate fecha) {
        DayOfWeek diaSemana =  fecha.getDayOfWeek();

        if (diaSemana.equals(DayOfWeek.SATURDAY) || diaSemana.equals(DayOfWeek.SUNDAY)) {
            throw new ExcepcionDiaInvalido(MENSAJE_DIA_INVALIDO);
        }
    }
}
