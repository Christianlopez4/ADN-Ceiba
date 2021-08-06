package com.ceiba.paciente.servicio;

import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.ceiba.paciente.servicio.testdatabuilder.PacienteTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioCrearPacienteTest {

    @Mock
    private RepositorioPaciente repositorioPaciente;

    @InjectMocks
    private ServicioCrearPaciente servicioCrearPaciente;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Agregar paciente correctamente")
    public void agregarPacienteTest1() {
        Long valorEsperado = 1L;
        Paciente paciente = new PacienteTestDataBuilder().build();

        Mockito.when(repositorioPaciente.crear(paciente)).thenReturn(paciente.getId());

        Long valorActual = servicioCrearPaciente.ejecutar(paciente);

        assertEquals(valorEsperado, valorActual);
    }

    @Test
    @DisplayName("No agregar paciente ID negativo")
    public void agregarPacienteTest2() {
        PacienteTestDataBuilder paciente = new PacienteTestDataBuilder().conID(-123L);
        try {
            paciente.build();
            fail();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Paciente.ID_POSITIVO);
        }
    }

    @Test
    @DisplayName("No agregar paciente nombre vacío")
    public void agregarPacienteTest3() {
        PacienteTestDataBuilder paciente = new PacienteTestDataBuilder().conNombre("");
        try {
            paciente.build();
            fail();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Paciente.NOMBRE_NO_VACIO);
        }
    }

    @Test
    @DisplayName("No agregar paciente apellido vacío")
    public void agregarPacienteTest4() {
        PacienteTestDataBuilder paciente = new PacienteTestDataBuilder().conApellido("");
        try {
            paciente.build();
            fail();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Paciente.APELLIDOS_NO_VACIO);
        }
    }

    @Test
    @DisplayName("No agregar paciente correo vacío")
    public void agregarPacienteTest5() {
        PacienteTestDataBuilder paciente = new PacienteTestDataBuilder().conApellido("");
        try {
            paciente.build();
            fail();
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), Paciente.CORREO_NO_VACIO);
        }
    }
}
