package com.ceiba.categoria.modelo.entidad;

import lombok.Getter;

@Getter
public class Categoria {

    private Integer id;
    private String titulo;
    private Double cuotaModeradora;
    private Double multa;

    public Categoria(Integer id, String titulo, Double cuotaModeradora, Double multa) {
        this.id = id;
        this.titulo = titulo;
        this.cuotaModeradora = cuotaModeradora;
        this.multa = multa;
    }
}
