package com.ceiba.cita.servicio;

import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.cita.utils.HolidayUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;


import java.time.LocalDate;

public class ServicioCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    @InjectMocks
    private ServicioCita servicioCita;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Cita sin sobrecosto")
    public void testValidarFestivo1(){
        Double costoEsperado = 3000.0;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();
        Double costoActual = servicioCita.validarFestivo(cita).getCosto();

        assertEquals(costoEsperado, costoActual);
    }

    @Test
    @DisplayName("Cita con sobrecosto")
    public void testValidarFestivo2() {
        Double costoEsperado = 6000.0;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,20)).build();
        Double costoActual = servicioCita.validarFestivo(cita).getCosto();

        assertEquals(costoEsperado, costoActual);
    }

    @Test
    @DisplayName("Cita sin múltiple cita el mismo día")
    public void testMultipleCita1() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(false);

        Cita citaActual = servicioCita.validarMultipleCitaElMismoDia(cita);

        assertEquals(cita, citaActual);
    }

    @Test
    @DisplayName("Cita con múltiple cita el mismo día")
    public void testMultipleCita2() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(true);

        try {
            servicioCita.validarMultipleCitaElMismoDia(cita);
            fail();
        } catch (Exception e) {
            assertEquals(e.getMessage(), Cita.MENSAJE_MULTIPLE_CITA);
        }

    }
}
