package com.ceiba.documento.modelo.entidad;

import lombok.Getter;

@Getter
public class Documento {

    private Integer id;
    private String titulo;

    public Documento(Integer id, String titulo) {
        this.id = id;
        this.titulo = titulo;
    }
}
