package com.ceiba.cita.excepcion;

public class ExcepcionMultipleCitaElMismoDia extends RuntimeException {

    public ExcepcionMultipleCitaElMismoDia(String mensaje) {
        super(mensaje);
    }
}
