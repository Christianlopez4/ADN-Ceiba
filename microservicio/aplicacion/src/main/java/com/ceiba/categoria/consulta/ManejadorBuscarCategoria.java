package com.ceiba.categoria.consulta;

import com.ceiba.categoria.modelo.dto.DtoCategoria;
import com.ceiba.categoria.puerto.dao.DaoCategoria;
import org.springframework.stereotype.Component;

@Component
public class ManejadorBuscarCategoria {

    private DaoCategoria daoCategoria;

    public ManejadorBuscarCategoria(DaoCategoria daoCategoria) {
        this.daoCategoria = daoCategoria;
    }

    public DtoCategoria ejecutar(Integer id) {
        return daoCategoria.buscar(id);
    }
}
