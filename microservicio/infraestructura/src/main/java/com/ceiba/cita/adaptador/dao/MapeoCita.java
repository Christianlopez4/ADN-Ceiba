package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {
    @Override
    public DtoCita mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        LocalDate fecha = rs.getDate("fecha").toLocalDate();
        LocalTime hora = rs.getTime("hora").toLocalTime();
        Double costo = rs.getDouble("costo");
        Long idPaciente = rs.getLong("id_paciente");
        String estado = rs.getString("estado");
        return new DtoCita(id, fecha, hora, costo, idPaciente, estado);
    }
}
