package com.ceiba.categoria.adaptador.dao;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoCategoria implements RowMapper<DtoCategoria>, MapperResult {

    @Override
    public DtoCategoria mapRow(ResultSet rs, int rowNum) throws SQLException {
        Integer id = rs.getInt("id");
        String titulo = rs.getString("titulo");
        Double cuotaModeradora = rs.getDouble("cuota_moderadora");
        Double multa = rs.getDouble("multa");

        return new DtoCategoria(id, titulo, cuotaModeradora, multa);
    }
}
