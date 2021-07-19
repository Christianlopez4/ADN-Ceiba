package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CategoriaTestDataBuilder;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.cita.servicio.testdatabuilder.PacienteTestDataBuilder;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ServicioCrearCitaPrueba {

   @Mock
   private RepositorioCita repositorioCita;

   @Mock
   private DaoPaciente daoPaciente;

   @Mock
   private DaoCategoria daoCategoria;

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

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        DtoPaciente dtoPaciente = new PacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new CategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(false);
        Mockito.when(repositorioCita.crear(cita)).thenReturn(cita.getId());

        Long valorActual = servicioCrearCita.ejecutar(cita);

        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @Disabled
    @DisplayName("No es posible agregar cita los días sábados")
    public void testAgregarCita2() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,17)).build();

        DtoPaciente dtoPaciente = new PacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new CategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(false);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(Cita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }
    }

    /*
    @Test
    @Disabled
    @DisplayName("No es posible agregar cita los días domingos")
    public void testAgregarCita3() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,18)).build();

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(Cita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }
    }

    @Test
    @Disabled
    @DisplayName("Agregar cita con sobrecosto")
    public void testAgregarCita4() {
        Double valorEsperado = 6000.0;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,20)).build();

        Mockito.when(repositorioCita.crear(cita)).thenReturn(cita.getId());
        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(false);

        servicioCrearCita.ejecutar(cita);

        Double valorActual = cita.getCosto();

        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @Disabled
    @DisplayName("No es posible agregar cita por múltiple cita el mismo día")
    public void testAgregarCita5() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(true);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionMultipleCitaElMismoDia e) {
            assertEquals(ServicioCrearCita.MENSAJE_MULTIPLE_CITA, e.getMessage());
        }
    }


     */
}
















