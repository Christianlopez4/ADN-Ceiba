package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.excepcion.ExcepcionDiaInvalido;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.DtoCategoriaTestDataBuilder;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.cita.servicio.testdatabuilder.DtoPacienteTestDataBuilder;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class ServicioCrearCitaTest {

   @Mock
   private RepositorioCita repositorioCita;

   @Mock
   private DaoPaciente daoPaciente;

   @Mock
   private DaoCategoria daoCategoria;

   @Mock
   private ServicioCita servicioCita;

   @InjectMocks
   private ServicioCrearCita servicioCrearCita;

   @Before
   public void init() {
       MockitoAnnotations.initMocks(this);
   }

    @Test
    @DisplayName("Agregar cita sin sobrecostos")
    public void testAgregarCita1() {

        Long valorEsperado = 1L;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(servicioCita.validarFestivo(cita)).thenReturn(cita);
        Mockito.when(servicioCita.validarMultipleCitaElMismoDia(cita)).thenReturn(cita);
        Mockito.when(repositorioCita.crear(cita)).thenReturn(1L);

        Long valorActual = servicioCrearCita.ejecutar(cita);
        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @DisplayName("No es posible agregar cita los d??as s??bados")
    public void testAgregarCita2() {

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,17));

        try {
            citaTestDataBuilder.build();
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(Cita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }

    }

    @Test
    @DisplayName("No es posible agregar cita los d??as domingos")
    public void testAgregarCita3() {

        CitaTestDataBuilder citaTestDataBuilder = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,18));

        try {
            citaTestDataBuilder.build();
            fail();
        } catch (ExcepcionDiaInvalido e) {
            assertEquals(Cita.MENSAJE_DIA_INVALIDO, e.getMessage());
        }
    }

    @Test
    @DisplayName("No es posible agregar cita por m??ltiple cita el mismo d??a")
    public void testAgregarCita4() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();

        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(servicioCita.validarFestivo(cita)).thenReturn(cita);
        Mockito.when(servicioCita.validarMultipleCitaElMismoDia(cita)).thenThrow(new ExcepcionMultipleCitaElMismoDia(Cita.MENSAJE_MULTIPLE_CITA));
        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(true);

        try {
            servicioCrearCita.ejecutar(cita);
            fail();
        } catch (ExcepcionMultipleCitaElMismoDia e) {
            assertEquals(Cita.MENSAJE_MULTIPLE_CITA, e.getMessage());
        }
    }

}
















