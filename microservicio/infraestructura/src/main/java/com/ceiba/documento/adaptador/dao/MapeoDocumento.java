package com.ceiba.documento.adaptador.dao;

import com.ceiba.documento.modelo.dto.DtoDocumento;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDocumento implements RowMapper<DtoDocumento>, MapperResult {

    @Override
    public DtoDocumento mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        return new DtoDocumento(id, titulo);
    }
}
