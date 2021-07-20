package com.ceiba.paciente.servicio;

import com.ceiba.paciente.modelo.entidad.Paciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.ceiba.paciente.servicio.testdatabuilder.PacienteTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
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
    public void agregarPacienteTest1() {
        Long valorEsperado = 1L;
        Paciente paciente = new PacienteTestDataBuilder().build();

        Mockito.when(repositorioPaciente.crear(paciente)).thenReturn(paciente.getId());

        Long valorActual = servicioCrearPaciente.ejecutar(paciente);

        assertEquals(valorEsperado, valorActual);
    }
}
