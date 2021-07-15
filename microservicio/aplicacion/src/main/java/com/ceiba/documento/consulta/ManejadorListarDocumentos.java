package com.ceiba.documento.consulta;

import com.ceiba.documento.modelo.dto.DtoDocumento;
import com.ceiba.documento.puerto.dao.DaoDocumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarDocumentos {

    @Autowired
    private DaoDocumento daoDocumento;

    public List<DtoDocumento> listar() {
        return this.daoDocumento.listar();
    }
}
