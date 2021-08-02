package com.ceiba.cita.servicio;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.cita.modelo.entidad.Cita;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.testdatabuilder.CitaTestDataBuilder;
import com.ceiba.cita.servicio.testdatabuilder.DtoCategoriaTestDataBuilder;
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
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class ServicioCancelarCitaTest {

    @Mock
    private RepositorioCita repositorioCita;

    @Mock
    private DaoCita daoCita;

    @Mock
    private DaoPaciente daoPaciente;

    @Mock
    private DaoCategoria daoCategoria;

    @Mock
    private ServicioCita servicioCita;

    @InjectMocks
    private ServicioCancelarCita servicioCancelarCita;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Cancelar cita con multa")
    public void testCancelarCita1() {
        Integer idCita = 1;
        Double costoEsperado = 5000.0;

        DtoCita dtoCita = new CitaTestDataBuilder().conFecha(LocalDate.now()).buildDto();
        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();
        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoCita.buscar(idCita)).thenReturn(dtoCita);
        Mockito.when(daoPaciente.buscar(dtoCita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);

        Cita cita = this.servicioCancelarCita.ejecutar(idCita);
        assertEquals(costoEsperado, cita.getCosto());
    }

    @Test
    @DisplayName("Cancelar cita sin multa")
    public void testCancelarCita2() {
        Integer idCita = 1;
        Double costoEsperado = 0.0;

        DtoCita dtoCita = new CitaTestDataBuilder().conFecha(LocalDate.now().minusDays(3)).buildDto();
        DtoPaciente dtoPaciente = new DtoPacienteTestDataBuilder().build();
        DtoCategoria dtoCategoria = new DtoCategoriaTestDataBuilder().build();

        Mockito.when(daoCita.buscar(idCita)).thenReturn(dtoCita);
        Mockito.when(daoPaciente.buscar(dtoCita.getIdPaciente())).thenReturn(dtoPaciente);
        Mockito.when(daoCategoria.buscar(dtoPaciente.getIdCategoria())).thenReturn(dtoCategoria);

        Cita cita = this.servicioCancelarCita.ejecutar(idCita);
        assertEquals(costoEsperado, cita.getCosto());
    }
}
