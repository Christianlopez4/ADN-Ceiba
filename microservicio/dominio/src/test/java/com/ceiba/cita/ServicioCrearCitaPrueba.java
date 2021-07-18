package com.ceiba.cita;

import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ServicioCrearCitaPrueba {

   @Mock
   private RepositorioCita repositorioCita;

   @InjectMocks
   private ServicioCrearCita servicioCrearCita;

   @Before
   public void init() {
       MockitoAnnotations.initMocks(this);
   }

    @Test
    @DisplayName("Agregar cita sin sobrecosto")
    public void testAgregarCita1() {

        Long valorEsperado = 1L;

        Long id = 1L;
        LocalDate fecha = LocalDate.of(2021, 7, 19);
        LocalTime hora = LocalTime.of(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        Mockito.when(repositorioCita.crear(cita)).thenReturn(id);
        Mockito.when(repositorioCita.existeMultipleCita(idPaciente, fecha)).thenReturn(false);

        Long valorActual = servicioCrearCita.ejecutar(cita);

        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @DisplayName("No es posible agregar cita los días sábados")
    public void testAgregarCita2() {
        Long id = 1L;
        LocalDate fecha = LocalDate.of(2021, 7, 17);
        LocalTime hora = LocalTime.of(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(ServicioCrearCita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }
    }

    @Test
    @DisplayName("No es posible agregar cita los días domingos")
    public void testAgregarCita4() {
        Long id = 1L;
        LocalDate fecha = LocalDate.of(2021, 7, 18);
        LocalTime hora = LocalTime.of(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(ServicioCrearCita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }
    }

    @Test
    @DisplayName("Agregar cita con sobrecosto")
    public void testAgregarCita5() {
        Double valorEsperado = 6000.0;

        Long id = 1L;
        LocalDate fecha = LocalDate.of(2021, 7, 20);
        LocalTime hora = LocalTime.of(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        Mockito.when(repositorioCita.crear(cita)).thenReturn(id);
        Mockito.when(repositorioCita.existeMultipleCita(idPaciente, fecha)).thenReturn(false);

        servicioCrearCita.ejecutar(cita);

        Double valorActual = cita.getCosto();

        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @DisplayName("No es posible agregar cita por múltiple cita el mismo día")
    public void testAgregarCita6() {
        Long id = 1L;
        LocalDate fecha = LocalDate.of(2021, 7, 19);
        LocalTime hora = LocalTime.of(14, 00, 00);
        Double costo = 3000.0;
        Long idPaciente = 123L;
        String estado = "ACTIVA";

        Cita cita = new Cita(id, fecha, hora, costo, idPaciente, estado);

        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(true);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionMultipleCitaElMismoDia e) {
            assertEquals(ServicioCrearCita.MENSAJE_MULTIPLE_CITA, e.getMessage());
        }
    }

}
















