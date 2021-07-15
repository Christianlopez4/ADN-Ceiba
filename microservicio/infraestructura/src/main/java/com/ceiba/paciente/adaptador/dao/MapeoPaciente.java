package com.ceiba.paciente.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.paciente.modelo.dto.DtoPaciente;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoPaciente implements RowMapper<DtoPaciente>, MapperResult {
    @Override
    public DtoPaciente mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        Date fechaNacimiento = rs.getDate("fecha_nacimiento");
        String correo = rs.getString("correo_electronico");
        Long telefono = rs.getLong("telefono");
        Integer idCategoria = rs.getInt("id_categoria");
        Integer idDocumento = rs.getInt("id_categoria");

        return new DtoPaciente(id, nombre, apellidos, fechaNacimiento, correo, telefono, idCategoria, idDocumento);
    }
}
