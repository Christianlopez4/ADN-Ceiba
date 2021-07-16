package com.ceiba.cita.adaptador.dao;

import com.ceiba.cita.modelo.dto.DtoCita;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

public class MapeoCita implements RowMapper<DtoCita>, MapperResult {
    @Override
    public DtoCita mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        Date fecha = rs.getDate("fecha");
        Time hora = rs.getTime("hora");
        Double costo = rs.getDouble("costo");
        Long idPaciente = rs.getLong("id_paciente");
        String estado = rs.getString("estado");
        return new DtoCita(id, fecha, hora, costo, idPaciente, estado);
    }
}
