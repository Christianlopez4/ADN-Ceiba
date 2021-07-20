package com.ceiba.cita.servicio.testdatabuilder;

import com.ceiba.categoria.modelo.dto.DtoCategoria;

public class DtoCategoriaTestDataBuilder {

    private Integer id;
    private String titulo;
    private Double cuotaModeradora;
    private Double multa;

    public DtoCategoriaTestDataBuilder() {
        this.id = 1;
        this.titulo = "A";
        this.cuotaModeradora = 3000.0;
        this.multa = 5000.0;
    }

    public DtoCategoriaTestDataBuilder conCuotaModeradora(Double cuotaModeradora) {
        this.cuotaModeradora = cuotaModeradora;
        return this;
    }

    public DtoCategoriaTestDataBuilder conMulta(Double multa) {
        this.multa = multa;
        return this;
    }

    public DtoCategoria build() {
        return new DtoCategoria(id, titulo, cuotaModeradora, multa);
    }
}
