package com.ceiba.categoria.puerto.dao;

import com.ceiba.categoria.modelo.dto.DtoCategoria;

import java.util.List;

public interface DaoCategoria {

    List<DtoCategoria> listar();

    DtoCategoria buscar(Integer id);
}
