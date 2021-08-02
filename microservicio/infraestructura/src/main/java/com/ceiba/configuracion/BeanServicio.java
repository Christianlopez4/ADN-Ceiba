package com.ceiba.configuracion;

import com.ceiba.categoria.puerto.dao.DaoCategoria;
import com.ceiba.cita.puerto.dao.DaoCita;
import com.ceiba.cita.puerto.repositorio.RepositorioCita;
import com.ceiba.cita.servicio.ServicioActualizarCita;
import com.ceiba.cita.servicio.ServicioCancelarCita;
import com.ceiba.cita.servicio.ServicioCita;
import com.ceiba.cita.servicio.ServicioCrearCita;
import com.ceiba.paciente.puerto.dao.DaoPaciente;
import com.ceiba.paciente.puerto.repositorio.RepositorioPaciente;
import com.ceiba.paciente.servicio.ServicioCrearPaciente;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {
	
    @Bean
    public ServicioCrearPaciente servicioCrearPaciente(RepositorioPaciente repositorioPaciente) {
        return new ServicioCrearPaciente(repositorioPaciente);
    }

    @Bean
    public ServicioCrearCita servicioCrearCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, ServicioCita servicioCita) {
        return new ServicioCrearCita(repositorioCita, daoPaciente, daoCategoria, servicioCita);
    }

    @Bean
    public ServicioActualizarCita servicioActualizarCita(RepositorioCita repositorioCita, ServicioCita servicioCita) {
        return new ServicioActualizarCita(repositorioCita, servicioCita);
    }

    @Bean
    public ServicioCita servicioCita(RepositorioCita repositorioCita) {
        return new ServicioCita(repositorioCita);
    }

    @Bean
    public ServicioCancelarCita servicioCancelarCita(RepositorioCita repositorioCita, DaoPaciente daoPaciente, DaoCategoria daoCategoria, DaoCita daoCita) {
        return new ServicioCancelarCita(repositorioCita, daoPaciente, daoCategoria, daoCita);
    }
}
