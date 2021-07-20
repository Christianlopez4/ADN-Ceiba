package com.ceiba.cita.modelo.entidad;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
public class Cita {

    public static final String MENSAJE_DIA_INVALIDO = "No es posible agendar citas los días sábados o domingos";

    private Integer id;
    private LocalDate fecha;
    private LocalTime hora;
    private Double costo;
    private Long idPaciente;
    private String estado;

    public Cita(Integer id, LocalDate fecha, LocalTime hora, Double costo, Long idPaciente, String estado) {
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

    public boolean esCancelacion() {
        return "CANCELADA".equals(this.estado);
    }
}
