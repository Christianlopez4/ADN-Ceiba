package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.excepcion.ExcepcionMultipleCitaElMismoDia;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ServicioActualizarCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    @Mock
    private DaoPaciente daoPaciente;

    @Mock
    private DaoCategoria daoCategoria;

    @Mock
    private DaoCita daoCita;

    @Mock
    private ServicioCita servicioCita;

    @InjectMocks
    private ServicioActualizarCita servicioActualizarCita;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Actualizar cita sin sobrecostos")
    public void testActualizarCita1() {

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();
        Mockito.when(repositorioCita.existeMultipleCita(cita.getIdPaciente(), cita.getFecha())).thenReturn(false);
        try {
            servicioActualizarCita.ejecutar(cita);
        } catch (Exception e) {
            fail();
        }

    }

    @Test
    @DisplayName("No es posible agregar cita por múltiple cita el mismo día")
    public void testActualizarCita2() {
        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).build();
        Mockito.when(servicioCita.validarFestivo(cita)).thenReturn(cita);
        Mockito.when(servicioCita.validarMultipleCitaElMismoDia(cita)).thenThrow(new ExcepcionMultipleCitaElMismoDia(Cita.MENSAJE_MULTIPLE_CITA));
        try {
            servicioActualizarCita.ejecutar(cita);
            fail();
        } catch (ExcepcionMultipleCitaElMismoDia e) {
            assertEquals(Cita.MENSAJE_MULTIPLE_CITA, e.getMessage());
        }
    }

    @Test
    @DisplayName("Cancelar cita con multa")
    public void testActualizarCita4() {
        Double valorEsperado = 5000.0;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).conEstado("CANCELADA").build();

        DtoCita dtoCita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).conEstado("CANCELADA").buildDto();

        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(daoCita.buscar(cita.getId())).thenReturn(dtoCita);

        servicioActualizarCita.ejecutar(cita);

        Double valorActual = cita.getCosto();
        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @DisplayName("Cancelar cita sin multa")
    public void testActualizarCita5() {
        Double valorEsperado = 0.0;

        Cita cita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,19)).conEstado("CANCELADA").build();

        DtoCita dtoCita = new CitaTestDataBuilder().conFecha(LocalDate.of(2021,7,23)).conEstado("CANCELADA").buildDto();

        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();

        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoPaciente.buscar(cita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);
        Mockito.when(daoCita.buscar(cita.getId())).thenReturn(dtoCita);

        servicioActualizarCita.ejecutar(cita);

        Double valorActual = cita.getCosto();
        assertEquals(valorEsperado, valorActual);
    }
}













