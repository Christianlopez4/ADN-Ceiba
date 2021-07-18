package com.ceiba.cita;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

public class CitaPrueba {

   @Mock
   private RepositorioCita repositorioCita;

   @InjectMocks
   private ServicioCrearCita servicioCrearCita;

   @Before
   public void init() {
       MockitoAnnotations.initMocks(this);
   }

    @Test
    @Disabled
    public void testAgregarCita1() {
       /*
        Long valorEsperado = 1L;

        Long id = 1L;
        Date fecha = new Date(2021, 7, 17);
        Time hora = new Time(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        //Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        Mockito.when(repositorioCita.crear(cita)).thenReturn(id);

        Long valorActual = servicioCrearCita.ejecutar(cita);

        assertEquals(valorEsperado, valorActual);

        */
    }

}
















